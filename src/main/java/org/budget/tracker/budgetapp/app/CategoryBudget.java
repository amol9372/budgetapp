package org.budget.tracker.budgetapp.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CategoryBudget extends BaseAppEntity {

  private String name;
  private Boolean userDefined;
  private BigDecimal allocated;
  private BigDecimal used;
  private Integer budgetId;
  private LocalDateTime lastUpdated;
  // TODO last operation
  // TODO status
  private Boolean autoDeduct;

  private LocalDate autoDeductOn;

  private String subCategory;

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

  public Boolean getAutoDeduct() {
    return autoDeduct;
  }

  public void setAutoDeduct(Boolean autoDeduct) {
    this.autoDeduct = autoDeduct;
  }

  public LocalDate getAutoDeductOn() {
    return autoDeductOn;
  }

  public void setAutoDeductOn(LocalDate autoDeductOn) {
    this.autoDeductOn = autoDeductOn;
  }

  public Integer getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(Integer budgetId) {
    this.budgetId = budgetId;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }
}
