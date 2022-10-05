package org.budget.tracker.budgetapp.rest.request;

public class CreateBudgetRequest extends BaseRequest{

  private String name;
  private String currency;
  private Double moneyAssigned;
  private String createdBy; // email
  private String cycle;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Double getMoneyAssigned() {
    return moneyAssigned;
  }

  public void setMoneyAssigned(Double moneyAssigned) {
    this.moneyAssigned = moneyAssigned;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getCycle() {
    return cycle;
  }

  public void setCycle(String cycle) {
    this.cycle = cycle;
  }
}
