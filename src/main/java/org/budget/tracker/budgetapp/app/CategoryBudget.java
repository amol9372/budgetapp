package org.budget.tracker.budgetapp.app;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CategoryBudget {

  private String name;
  private Boolean userDefined;
  private BigDecimal allocated;
  private BigDecimal used;
  private LocalDateTime lastUpdated;
  // TODO last operation
  // TODO status
  private String spending;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getUserDefined() {
    return userDefined;
  }

  public void setUserDefined(Boolean userDefined) {
    this.userDefined = userDefined;
  }

  public BigDecimal getAllocated() {
    return allocated;
  }

  public void setAllocated(BigDecimal allocated) {
    this.allocated = allocated;
  }

  public BigDecimal getUsed() {
    return used;
  }

  public void setUsed(BigDecimal used) {
    this.used = used;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public String getSpending() {
    return spending;
  }

  public void setSpending(String spending) {
    this.spending = spending;
  }
}
