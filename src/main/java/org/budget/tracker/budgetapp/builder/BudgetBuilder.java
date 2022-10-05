package org.budget.tracker.budgetapp.builder;

import org.budget.tracker.budgetapp.db.JBudget;
import org.budget.tracker.budgetapp.db.JCategoryBudget;
import org.budget.tracker.budgetapp.rest.request.CategoryBudgetRequest;
import org.budget.tracker.budgetapp.rest.request.CreateBudgetRequest;

import java.time.LocalDateTime;
import java.util.Objects;

public class BudgetBuilder {

  public static JCategoryBudget with(CategoryBudgetRequest request) {

    var jCategoryBudget = new JCategoryBudget();
    jCategoryBudget.setBudgetId(request.getBudgetId());
    jCategoryBudget.setId(request.getId());
    jCategoryBudget.setAllocated(request.getAllocation().doubleValue());
    jCategoryBudget.setUserDefined(request.getUserDefined());
    jCategoryBudget.setAutoDeduct(request.getAutoDeduct());
    jCategoryBudget.setAutoDeductOn(request.getAutoDeductionOn());
    return jCategoryBudget;
  }

  public static JBudget with(CreateBudgetRequest request) {
    var jBudget = new JBudget();

    if (Objects.nonNull(request.getId())) {
      jBudget.setId(request.getId());
    }

    jBudget.setCurrency(request.getCurrency());
    jBudget.setMoneyAssigned(request.getMoneyAssigned());
    jBudget.setName(request.getName());
    jBudget.setCycle(request.getCycle());
    jBudget.setCreatedOn(LocalDateTime.now());
    jBudget.setUserId(request.getUserId());
    return jBudget;
  }
}
