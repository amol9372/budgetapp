package org.budget.tracker.budgetapp.app;

import java.util.List;

public class ExpenseCategory extends BaseAppEntity {

    private String name;
    private List<String> subCategories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }
}
