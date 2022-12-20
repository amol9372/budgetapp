package org.budget.tracker.budgetapp.db;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "category_budget")
public class JCategoryBudget {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "budget_id")
  private Integer budgetId;

  @Column(name = "user_defined")
  private Boolean userDefined;

  @Column(name = "category_id")
  private Integer categoryId;

  @Column(name = "sub_category")
  private String subCategory;

  @Column(name = "allocated")
  private Double allocated;

  @Column(name = "available")
  private Double available;

  @Column(name = "used")
  private Double used;

  @Column(name = "auto_deduct")
  private Boolean autoDeduct;

  @Column(name = "auto_deduct_on")
  private LocalDate autoDeductOn;

  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @Column(name = "updated_on")
  private LocalDateTime updatedOn;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(Integer budgetId) {
    this.budgetId = budgetId;
  }

  public Boolean getUserDefined() {
    return userDefined;
  }

  public void setUserDefined(Boolean userDefined) {
    this.userDefined = userDefined;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Double getAllocated() {
    return allocated;
  }

  public void setAllocated(Double allocated) {
    this.allocated = allocated;
  }

  public Double getUsed() {
    return used;
  }

  public void setUsed(Double used) {
    this.used = used;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public LocalDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(LocalDateTime updatedOn) {
    this.updatedOn = updatedOn;
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

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }

  public Double getAvailable() {
    return available;
  }

  public void setAvailable(Double available) {
    this.available = available;
  }
}
