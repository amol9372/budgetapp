package org.budget.tracker.budgetapp.rest.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CategoryBudgetRequest extends BaseRequest {

  private String category;
  private String subCategory;
  private Boolean userDefined;
  private BigDecimal allocated;
  private BigDecimal used;
  private Integer budgetId;
  private Boolean autoDeduct;  // auto deductible
  private LocalDate autoDeductionOn;  // auto deduction date

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
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

  public BigDecimal getUsed() {
    return used;
  }

  public void setUsed(BigDecimal used) {
    this.used = used;
  }
}
