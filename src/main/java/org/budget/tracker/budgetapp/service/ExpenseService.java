package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.app.Expense;
import org.budget.tracker.budgetapp.rest.request.CreateExpenseRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    void createExpense(CreateExpenseRequest request);

    List<Expense> getExpenses();

}
