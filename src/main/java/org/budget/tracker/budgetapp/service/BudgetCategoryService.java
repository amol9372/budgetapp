package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.app.CategoryBudget;
import org.budget.tracker.budgetapp.rest.request.CategoryBudgetRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface BudgetCategoryService {

  @Transactional
  void editBudgetCategory(Integer categoryId, CategoryBudgetRequest request);

  void createBudgetCategory(CategoryBudgetRequest request);

  List<CategoryBudget> getCategoryBudgets(Integer budgetId);

    void deleteCategoryBudget(Integer categoryId);
}