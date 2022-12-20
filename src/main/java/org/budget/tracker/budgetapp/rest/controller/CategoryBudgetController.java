package org.budget.tracker.budgetapp.rest.controller;

import org.budget.tracker.budgetapp.app.CategoryBudget;
import org.budget.tracker.budgetapp.app.ExpenseCategory;
import org.budget.tracker.budgetapp.rest.request.CategoryBudgetRequest;
import org.budget.tracker.budgetapp.service.BudgetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("category-budget")
// @CrossOrigin(origins = {"http://localhost:3000, https://budget-tracker-4de96.web.app"}, allowedHeaders = "*", allowCredentials = "true")
public class CategoryBudgetController {

  @Autowired BudgetCategoryService budgetCategoryService;

  @PostMapping
  public void allocateBudget(@RequestBody CategoryBudgetRequest request) {
    budgetCategoryService.createBudgetCategory(request);
  }

  @PostMapping("expense-category")
  public ResponseEntity<Map<String, Integer>> expenseCategory(@RequestBody CategoryBudgetRequest request) {
    var categoryBudgetId = budgetCategoryService.createExpenseCategory(request);
    return ResponseEntity.status(201).body(Map.of("id", categoryBudgetId));
  }

  @PutMapping("{categoryId}")
  public ResponseEntity<CategoryBudget> editCategoryAllocation(@PathVariable Integer categoryId, @RequestBody CategoryBudgetRequest request) {
    var categoryBudget = budgetCategoryService.editBudgetCategory(categoryId, request);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoryBudget);
  }

  @GetMapping("{budgetId}")
  public List<CategoryBudget> fetchCategoryBudgets(@PathVariable Integer budgetId) {

    return budgetCategoryService.getCategoryBudgets(budgetId);
  }

  @GetMapping("categories/{budgetId}")
  public ResponseEntity<List<ExpenseCategory>> fetchCategories(@PathVariable Integer budgetId) {

    var categories = budgetCategoryService.getCategories(budgetId);
    return ResponseEntity.of(Optional.of(categories));
  }

  @DeleteMapping("{categoryId}")
  public void deleteCategoryBudget(@PathVariable Integer categoryId){
    budgetCategoryService.deleteCategoryBudget(categoryId);
  }
}
