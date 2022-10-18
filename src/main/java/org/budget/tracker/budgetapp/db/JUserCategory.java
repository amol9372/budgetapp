package org.budget.tracker.budgetapp.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_categories")
public class JUserCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "budget_id")
  private Integer budgetId;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(Integer budgetId) {
    this.budgetId = budgetId;
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
}
