package org.budget.tracker.budgetapp.rest.controller;

import org.budget.tracker.budgetapp.app.Budget;
import org.budget.tracker.budgetapp.rest.request.CreateBudgetRequest;
import org.budget.tracker.budgetapp.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("budget")
@CrossOrigin(origins = {"http://localhost:3000, https://budget-tracker-4de96.web.app"}, allowedHeaders = "*", allowCredentials = "true")
//@CrossOrigin("*")
public class BudgetController {


  @Autowired BudgetService budgetService;

  // create a budget
  @PostMapping
  public void createBudget(@RequestBody CreateBudgetRequest request) {
    budgetService.createBudget(request);
  }

  @GetMapping("all/{userId}")
  public List<Budget> fetchAllBudgets(@PathVariable Integer userId){
      return budgetService.fetchAllBudgets(userId);
  }

  @GetMapping("{budgetId}")
  public Budget fetchBudget(@PathVariable Integer budgetId){

    return budgetService.fetchBudget(budgetId);
  }

}
