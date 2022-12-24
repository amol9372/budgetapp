package org.budget.tracker.budgetapp.app;

import java.util.List;

public class CategoryBudgetResponse {

    private String subCategory;
    private List<CategoryBudget> categoryBudgets;

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public List<CategoryBudget> getCategoryBudgets() {
        return categoryBudgets;
    }

    public void setCategoryBudgets(List<CategoryBudget> categoryBudgets) {
        this.categoryBudgets = categoryBudgets;
    }
}
