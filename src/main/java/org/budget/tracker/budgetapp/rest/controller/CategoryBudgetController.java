package org.budget.tracker.budgetapp.rest.controller;

import org.budget.tracker.budgetapp.app.CategoryBudget;
import org.budget.tracker.budgetapp.rest.request.CategoryBudgetRequest;
import org.budget.tracker.budgetapp.service.BudgetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category-budget")
public class CategoryBudgetController {

  @Autowired BudgetCategoryService budgetCategoryService;

  // assign budget to a category OR
  @PostMapping
  public void allocateBudget(@RequestBody CategoryBudgetRequest request) {
    budgetCategoryService.upsertBudgetCategory(request);
  }

  @GetMapping
  public List<CategoryBudget> categoryBudgets() {
    return null;
  }
}
