package org.budget.tracker.budgetapp.app;

public enum BudgetCycle {
  PRO_RATED("prorated"),
  END_OF_MONTH("endofmonth");

  private final String value;

  BudgetCycle(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
