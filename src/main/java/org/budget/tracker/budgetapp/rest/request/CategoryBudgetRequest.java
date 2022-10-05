package org.budget.tracker.budgetapp.rest.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CategoryBudgetRequest extends BaseRequest {

  private String category;
  private Boolean userDefined;
  private BigDecimal allocation;
  private Integer budgetId;
  private Boolean autoDeduct;  // auto deductible
  private LocalDate autoDeductionOn;  // auto deduction date

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Boolean getUserDefined() {
    return userDefined;
  }

  public void setUserDefined(Boolean userDefined) {
    this.userDefined = userDefined;
  }

  public BigDecimal getAllocation() {
    return allocation;
  }

  public void setAllocation(BigDecimal allocation) {
    this.allocation = allocation;
  }

  public Integer getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(Integer budgetId) {
    this.budgetId = budgetId;
  }

  public Boolean getAutoDeduct() {
    return autoDeduct;
  }

  public void setAutoDeduct(Boolean autoDeduct) {
    this.autoDeduct = autoDeduct;
  }

  public LocalDate getAutoDeductionOn() {
    return autoDeductionOn;
  }

  public void setAutoDeductionOn(LocalDate autoDeductionOn) {
    this.autoDeductionOn = autoDeductionOn;
  }
}
