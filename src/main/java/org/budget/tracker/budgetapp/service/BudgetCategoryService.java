package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.rest.request.CategoryBudgetRequest;
import org.springframework.stereotype.Service;

@Service
public interface BudgetCategoryService {

  void upsertBudgetCategory(CategoryBudgetRequest request);
}
