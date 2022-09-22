package org.budget.tracker.budgetapp.rest.controller;

import org.budget.tracker.budgetapp.rest.request.CreateExpenseRequest;
import org.budget.tracker.budgetapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping
    public String healthCheck() {
        return "This controller is working";
    }

    @PostMapping
    public void createExpense(@RequestBody CreateExpenseRequest request) {
        expenseService.createExpense(request);
    }

}
