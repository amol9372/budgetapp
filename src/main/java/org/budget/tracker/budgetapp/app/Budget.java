package org.budget.tracker.budgetapp.app;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Budget extends BaseAppEntity {

  private String name;
  private String currency;
  private BigDecimal current;
  private BigDecimal moneyAssigned;
  private String monthYear;
  private LocalDateTime createdOn;
  private String createdBy; // email

  private String cycle; // EOM  or Pro-rate

  private String status; // overspent, can be assigned, level
  // TODO previous budget settings

  //    public static void main(String[] args) {
  //        CurrencyUnit usd = Monetary.getCurrency("USD");
  //        MonetaryAmount fstAmtUSD = Monetary.getDefaultAmountFactory()
  //                .setCurrency(usd)
  //                .setNumber(200)
  //                .create();
  //        Money moneyof = Money.of(12.34, usd);
  //
  //        System.out.println(moneyof.getNumberStripped());
  //
  //    }

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

  public BigDecimal getMoneyAssigned() {
    return moneyAssigned;
  }

  public void setMoneyAssigned(BigDecimal moneyAssigned) {
    this.moneyAssigned = moneyAssigned;
  }

  public String getMonthYear() {
    return monthYear;
  }

  public void setMonthYear(String monthYear) {
    this.monthYear = monthYear;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public BigDecimal getCurrent() {
    return current;
  }

  public void setCurrent(BigDecimal current) {
    this.current = current;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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
