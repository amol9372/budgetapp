package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.rest.request.CreateBudgetRequest;
import org.springframework.stereotype.Service;

@Service
public interface BudgetService {

  void createBudget(CreateBudgetRequest request);
}
