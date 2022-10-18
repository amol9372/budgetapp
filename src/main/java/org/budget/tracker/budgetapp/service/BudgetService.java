package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.app.Budget;
import org.budget.tracker.budgetapp.rest.request.CreateBudgetRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BudgetService {

  void createBudget(CreateBudgetRequest request);

  List<Budget> fetchAllBudgets(Integer userId);

  Budget fetchBudget(Integer budgetId);
}
