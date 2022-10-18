package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.app.Budget;
import org.budget.tracker.budgetapp.builder.BudgetBuilder;
import org.budget.tracker.budgetapp.db.JBudget;
import org.budget.tracker.budgetapp.db.JCategory;
import org.budget.tracker.budgetapp.db.JCategoryBudget;
import org.budget.tracker.budgetapp.repository.BudgetJpaRepository;
import org.budget.tracker.budgetapp.repository.CategoryBudgetJpaRepository;
import org.budget.tracker.budgetapp.repository.CategoryJpaRepository;
import org.budget.tracker.budgetapp.rest.request.CreateBudgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

  @Autowired BudgetJpaRepository budgetJpaRepository;

  @Autowired
  CategoryJpaRepository categoryJpaRepository;

  @Autowired
  CategoryBudgetJpaRepository categoryBudgetJpaRepository;

  @Override
  @Transactional
  public void createBudget(CreateBudgetRequest request) {

    JBudget jBudget = BudgetBuilder.with(request);
    budgetJpaRepository.save(jBudget);

    // add user categories
    List<JCategory> jCategories = categoryJpaRepository.findAll();
    List<JCategoryBudget> jCategoryBudgets = BudgetBuilder.with(jCategories, jBudget);
    categoryBudgetJpaRepository.saveAll(jCategoryBudgets);
    categoryBudgetJpaRepository.flush();
  }

  @Override
  public List<Budget> fetchAllBudgets(Integer userId) {

    List<JBudget> jBudgets = budgetJpaRepository.findByUserId(userId);

    return BudgetBuilder.with(jBudgets);
  }

  @Override
  public Budget fetchBudget(Integer budgetId) {
    Optional<JBudget> jBudget = budgetJpaRepository.findById(budgetId);
    return BudgetBuilder.with(jBudget.get());
  }
}
