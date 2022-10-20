package org.budget.tracker.budgetapp.rest.controller;

import org.budget.tracker.budgetapp.app.CategoryBudget;
import org.budget.tracker.budgetapp.rest.request.CategoryBudgetRequest;
import org.budget.tracker.budgetapp.service.BudgetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category-budget")
// @CrossOrigin(origins = {"http://localhost:3000, https://budget-tracker-4de96.web.app"}, allowedHeaders = "*", allowCredentials = "true")
public class CategoryBudgetController {

  @Autowired BudgetCategoryService budgetCategoryService;

  @PostMapping
  public void allocateBudget(@RequestBody CategoryBudgetRequest request) {
    budgetCategoryService.createBudgetCategory(request);
  }

  @PostMapping("{categoryId}")
  public void editCategoryAllocation(@PathVariable Integer categoryId, @RequestBody CategoryBudgetRequest request) {
    budgetCategoryService.editBudgetCategory(categoryId, request);
  }

  @GetMapping("{budgetId}")
  public List<CategoryBudget> fetchCategoryBudgets(@PathVariable Integer budgetId) {

    return budgetCategoryService.getCategoryBudgets(budgetId);
  }

  @DeleteMapping("{categoryId}")
  public void deleteCategoryBudget(@PathVariable Integer categoryId){
    budgetCategoryService.deleteCategoryBudget(categoryId);
  }
}
