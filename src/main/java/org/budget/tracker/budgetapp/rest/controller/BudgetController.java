package org.budget.tracker.budgetapp.rest.controller;

import org.budget.tracker.budgetapp.rest.request.CreateBudgetRequest;
import org.budget.tracker.budgetapp.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("budget")
public class BudgetController {

  @Autowired
  BudgetService budgetService;

  // create a budget
  @PostMapping
  public void createBudget(@RequestBody CreateBudgetRequest request) {
    budgetService.createBudget(request);
  }
}
