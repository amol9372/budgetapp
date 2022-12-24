package org.budget.tracker.budgetapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.budget.tracker.budgetapp.app.CategoryBudget;
import org.budget.tracker.budgetapp.app.CategoryBudgetResponse;
import org.budget.tracker.budgetapp.app.ExpenseCategory;
import org.budget.tracker.budgetapp.builder.BudgetBuilder;
import org.budget.tracker.budgetapp.builder.CategoryBudgetBuilder;
import org.budget.tracker.budgetapp.db.JBudget;
import org.budget.tracker.budgetapp.db.JCategory;
import org.budget.tracker.budgetapp.db.JCategoryBudget;
import org.budget.tracker.budgetapp.db.JUserCategory;
import org.budget.tracker.budgetapp.repository.BudgetJpaRepository;
import org.budget.tracker.budgetapp.repository.CategoryBudgetJpaRepository;
import org.budget.tracker.budgetapp.repository.CategoryJpaRepository;
import org.budget.tracker.budgetapp.repository.UserCategoryJpaRepository;
import org.budget.tracker.budgetapp.rest.request.CategoryBudgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryBudgetServiceImpl implements BudgetCategoryService {

  private final Logger logger = LogManager.getLogger(this.getClass());

  @Autowired CategoryBudgetJpaRepository categoryBudgetJpaRepository;

  @Autowired CategoryJpaRepository categoryJpaRepository;

  @Autowired UserCategoryJpaRepository userCategoryJpaRepository;

  @Autowired BudgetJpaRepository budgetJpaRepository;

  @Override
  @Transactional
  public void createBudgetCategory(CategoryBudgetRequest request) {

    Integer categoryId;
    Double previousAllocation = 0.0;
    if (request.getUserDefined()) {
      var userCategory = createUserCategory(request);
      categoryId = userCategory.getId();
    } else {
      categoryId = categoryJpaRepository.findByName(request.getCategory()).getId();
      previousAllocation =
          categoryBudgetJpaRepository
              .findByBudgetIdAndCategoryId(request.getBudgetId(), categoryId)
              .getAllocated();
    }

    JCategoryBudget jCategoryBudget = BudgetBuilder.with(request);
    jCategoryBudget.setCategoryId(categoryId);
    categoryBudgetJpaRepository.save(jCategoryBudget);

    /*
    update budget
     */
    updateBudget(request, previousAllocation);
  }

  @Override
  @Transactional
  public CategoryBudget editBudgetCategory(Integer categoryId, CategoryBudgetRequest request) {

    JCategoryBudget jCategoryBudget = categoryBudgetJpaRepository.findById(categoryId).get();
    var previousAllocation = jCategoryBudget.getAllocated();
    var prevAvailable = jCategoryBudget.getUsed();

    jCategoryBudget.setAllocated(request.getAllocated().doubleValue());

    if (previousAllocation > request.getAllocated().doubleValue()) {
      jCategoryBudget.setUsed(
          prevAvailable - (previousAllocation - request.getAllocated().doubleValue()));
    } else {
      jCategoryBudget.setUsed(
          prevAvailable + (request.getAllocated().doubleValue()) - previousAllocation);
    }

    var savedCategoryBudget = categoryBudgetJpaRepository.save(jCategoryBudget);
    updateBudget(request, previousAllocation);

    return CategoryBudgetBuilder.with(savedCategoryBudget, request);
  }

  // @Override
  private List<CategoryBudget> getCategoryBudgetsV1(Integer budgetId) {

    // if user_categories table is empty, get from categories table
    List<JCategoryBudget> jCategoryBudgets = categoryBudgetJpaRepository.findByBudgetId(budgetId);
    List<JCategory> jCategories = categoryJpaRepository.findAll();
    Optional<List<JUserCategory>> jUserCategories =
        userCategoryJpaRepository.findByBudgetId(budgetId);

    return CategoryBudgetBuilder.with(jCategoryBudgets, jCategories, jUserCategories);
  }

  @Override
  public List<CategoryBudgetResponse> getCategoryBudgets(Integer budgetId) {

    // if user_categories table is empty, get from categories table
    List<JCategoryBudget> jCategoryBudgets = categoryBudgetJpaRepository.findByBudgetId(budgetId);
    List<JCategory> jCategories = categoryJpaRepository.findAll();
    Optional<List<JUserCategory>> jUserCategories =
        userCategoryJpaRepository.findByBudgetId(budgetId);

    var categoryBudgets =
        CategoryBudgetBuilder.with(jCategoryBudgets, jCategories, jUserCategories);

    return categoryBudgets.stream()
        .map(
            categoryBudget -> {
              if (Objects.isNull(categoryBudget.getSubCategory())) {
                categoryBudget.setSubCategory("essentials");
              }
              return categoryBudget;
            })
        .collect(Collectors.groupingBy(CategoryBudget::getSubCategory))
        .entrySet()
        .stream()
        .map(
            entry -> {
              var categoryBudgetResponse = new CategoryBudgetResponse();
              if (Objects.isNull(entry.getKey())) {
                categoryBudgetResponse.setSubCategory("essentials");
              } else {
                categoryBudgetResponse.setSubCategory(entry.getKey());
              }
              categoryBudgetResponse.setCategoryBudgets(entry.getValue());
              return categoryBudgetResponse;
            })
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void deleteCategoryBudget(Integer categoryId) {
    /*
    Delete categories & current
    Check category is user defined or not
     */
    JCategoryBudget jCategoryBudget = categoryBudgetJpaRepository.findById(categoryId).get();
    if (jCategoryBudget.getUserDefined()) {
      userCategoryJpaRepository.deleteById(jCategoryBudget.getCategoryId());
      // remove from user categories also
    }
    categoryBudgetJpaRepository.deleteById(categoryId);

    /*
    Update the budget
     */
    Optional<JBudget> jBudget = budgetJpaRepository.findById(jCategoryBudget.getBudgetId());

    jBudget.ifPresent(
        budget -> {
          budget.setMoneyAvailable(budget.getMoneyAvailable() + jCategoryBudget.getAllocated());
          budgetJpaRepository.save(budget);
        });
  }

  @Override
  public List<ExpenseCategory> getCategories(Integer budgetId) {
    var categoryBudgets = getCategoryBudgetsV1(budgetId);

    var categoryMap =
        categoryBudgets.stream()
            .filter(category -> category.getSubCategory() != null)
            .collect(Collectors.groupingBy(CategoryBudget::getSubCategory));
    //                    Collectors.mapping(CategoryBudget::getName, Collectors.toList())));

    return categoryMap.entrySet().stream()
        .map(
            category -> {
              var expenseCategory = new ExpenseCategory();
              var id = category.getValue().get(0).getId();
              expenseCategory.setId(id);
              expenseCategory.setName(category.getKey());
              expenseCategory.setSubCategories(
                  category.getValue().stream()
                      .map(CategoryBudget::getName)
                      .collect(Collectors.toList()));
              return expenseCategory;
            })
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public Integer createExpenseCategory(CategoryBudgetRequest request) {

    Integer categoryId;
    var userCategory = createUserCategory(request);
    categoryId = userCategory.getId();

    JCategoryBudget jCategoryBudget = BudgetBuilder.with(request);
    jCategoryBudget.setCategoryId(categoryId);
    return categoryBudgetJpaRepository.save(jCategoryBudget).getId();
  }

  private void updateBudget(CategoryBudgetRequest request, Double previousAllocation) {
    var balance = request.getAllocated().doubleValue() - previousAllocation;

    Optional<JBudget> jBudget = budgetJpaRepository.findById(request.getBudgetId());
    jBudget.ifPresent(
        budget -> {
          budget.setMoneyAvailable(budget.getMoneyAvailable() - balance);
          budgetJpaRepository.save(budget);
        });
  }

  private JUserCategory createUserCategory(CategoryBudgetRequest request) {
    var jUserCategory = new JUserCategory();
    jUserCategory.setBudgetId(request.getBudgetId());
    jUserCategory.setName(request.getCategory());
    jUserCategory.setUserId(request.getUserId());
    jUserCategory.setCreatedOn(LocalDateTime.now());
    return userCategoryJpaRepository.save(jUserCategory);
  }
}
