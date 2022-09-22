package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.app.Expense;
import org.budget.tracker.budgetapp.builder.ExpenseBuilder;
import org.budget.tracker.budgetapp.db.JExpense;
import org.budget.tracker.budgetapp.db.JGroup;
import org.budget.tracker.budgetapp.repository.ExpenseJpaRepository;
import org.budget.tracker.budgetapp.repository.GroupJpaRepository;
import org.budget.tracker.budgetapp.rest.request.CreateExpenseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    GroupJpaRepository groupJpaRepository;

    @Autowired
    ExpenseJpaRepository expenseJpaRepository;

    @Override
    public void createExpense(CreateExpenseRequest request) {
        // Fetch group
        JGroup jGroup = groupJpaRepository.findByName(request.getGroup());
        JExpense expense = ExpenseBuilder.with(request, jGroup);
        JExpense savedExpense = expenseJpaRepository.save(expense);

        // push data in kafka -> elasticsearch
    }

    @Override
    public List<Expense> getExpenses() {

        // fetch expenses from Index
        return null;
    }


}
