package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.builder.BudgetBuilder;
import org.budget.tracker.budgetapp.db.JCategoryBudget;
import org.budget.tracker.budgetapp.repository.CategoryBudgetJpaRepository;
import org.budget.tracker.budgetapp.repository.CategoryJpaRepository;
import org.budget.tracker.budgetapp.repository.UserCategoryJpaRepository;
import org.budget.tracker.budgetapp.rest.request.CategoryBudgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetCategoryServiceImpl implements BudgetCategoryService {

  @Autowired CategoryBudgetJpaRepository categoryBudgetJpaRepository;

  @Autowired CategoryJpaRepository categoryJpaRepository;

  @Autowired UserCategoryJpaRepository userCategoryJpaRepository;

  @Override
  public void upsertBudgetCategory(CategoryBudgetRequest request) {

    /*
    Check if user defined or not
    */
    // if user-defined, get categoryId from user_budget_categories
    // if not, get categoryId from categories (by name)
    Integer categoryId;
    if (request.getUserDefined()) {
      categoryId =
          userCategoryJpaRepository
              .findByNameAndUserId(request.getCategory(), request.getUserId())
              .getId();
    } else {
      categoryId = categoryJpaRepository.findByName(request.getCategory()).getId();
    }

    JCategoryBudget jCategoryBudget = BudgetBuilder.with(request);
    jCategoryBudget.setCategoryId(categoryId);

    categoryBudgetJpaRepository.save(jCategoryBudget);
  }
}
