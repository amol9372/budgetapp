package org.budget.tracker.budgetapp.messaging.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Expense {

  private String name;
  private BigDecimal cost;
  private String category;
  private Integer categoryId;

  private Integer budgetId;
  private String subCategory;
  private String paidBy;
  private String group;
  private String createdBy;
  private LocalDateTime createdOn;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getPaidBy() {
    return paidBy;
  }

  public void setPaidBy(String paidBy) {
    this.paidBy = paidBy;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Integer getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(Integer budgetId) {
    this.budgetId = budgetId;
  }

  @Override
  public String toString() {
    return "Expense{" +
            "name='" + name + '\'' +
            ", cost=" + cost +
            ", category='" + category + '\'' +
            ", subCategory='" + subCategory + '\'' +
            ", paidBy='" + paidBy + '\'' +
            ", group='" + group + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdOn=" + createdOn +
            '}';
  }
}
