package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.app.CategoryBudget;
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
import java.util.Optional;

@Service
public class CategoryBudgetServiceImpl implements BudgetCategoryService {

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
  public void editBudgetCategory(Integer categoryId, CategoryBudgetRequest request) {

    JCategoryBudget jCategoryBudget = categoryBudgetJpaRepository.findById(categoryId).get();
    var previousAllocation = jCategoryBudget.getAllocated();
    jCategoryBudget.setAllocated(request.getAllocated().doubleValue());
    categoryBudgetJpaRepository.save(jCategoryBudget);

    updateBudget(request, previousAllocation);
  }

  @Override
  public List<CategoryBudget> getCategoryBudgets(Integer budgetId) {

    // if user_categories table is empty, get from categories table
    List<JCategoryBudget> jCategoryBudgets = categoryBudgetJpaRepository.findByBudgetId(budgetId);
    List<JCategory> jCategories = categoryJpaRepository.findAll();
    Optional<List<JUserCategory>> jUserCategories =
        userCategoryJpaRepository.findByBudgetId(budgetId);

    return CategoryBudgetBuilder.with(jCategoryBudgets, jCategories, jUserCategories);
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
