package org.budget.tracker.budgetapp.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "budget", schema = "budget_tracker")
public class JBudget {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String currency;

  @Column(name = "money_assigned")
  private Double moneyAssigned;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "cycle")
  private String cycle;

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

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getCycle() {
    return cycle;
  }

  public void setCycle(String cycle) {
    this.cycle = cycle;
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
