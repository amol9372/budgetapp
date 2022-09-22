package org.budget.tracker.budgetapp.app;

enum Category {

    GROCERY("groceries"), GENERAL("general"), ELECTRONICS("electronics"), RENT("rent"), DINING("dining");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}

