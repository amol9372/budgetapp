package org.budget.tracker.budgetapp.builder;

import org.budget.tracker.budgetapp.app.Budget;
import org.budget.tracker.budgetapp.db.JBudget;
import org.budget.tracker.budgetapp.db.JCategory;
import org.budget.tracker.budgetapp.db.JCategoryBudget;
import org.budget.tracker.budgetapp.rest.request.CategoryBudgetRequest;
import org.budget.tracker.budgetapp.rest.request.CreateBudgetRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BudgetBuilder {

  public static JCategoryBudget with(CategoryBudgetRequest request) {

    var jCategoryBudget = new JCategoryBudget();
    // jCategoryBudget.setId(request.getId());
    jCategoryBudget.setBudgetId(request.getBudgetId());
    jCategoryBudget.setId(request.getId());
    jCategoryBudget.setAllocated(request.getAllocated().doubleValue());
    jCategoryBudget.setUsed(request.getUsed().doubleValue());
    jCategoryBudget.setUserDefined(request.getUserDefined());
    jCategoryBudget.setAutoDeduct(request.getAutoDeduct());
    jCategoryBudget.setAutoDeductOn(request.getAutoDeductionOn());
    jCategoryBudget.setCreatedOn(LocalDateTime.now());
    return jCategoryBudget;
  }

  public static JBudget with(CreateBudgetRequest request) {
    var jBudget = new JBudget();

    if (Objects.nonNull(request.getId())) {
      jBudget.setId(request.getId());
    }

    jBudget.setCurrency(request.getCurrency());
    jBudget.setMoneyAssigned(request.getMoneyAssigned());
    jBudget.setMoneyAvailable(request.getMoneyAvailable());
    jBudget.setName(request.getName());
    jBudget.setCycle(request.getCycle());
    jBudget.setCreatedOn(LocalDateTime.now());
    jBudget.setUserId(request.getUserId());
    return jBudget;
  }

  public static List<Budget> with(List<JBudget> jBudgets) {
    return jBudgets.stream()
        .map(
                BudgetBuilder::with)
        .collect(Collectors.toList());
  }

  public static Budget with(JBudget jBudget){
      var budget = new Budget();
      budget.setId(jBudget.getId());
      budget.setName(jBudget.getName());
      budget.setCurrency(jBudget.getCurrency());
      budget.setMoneyAssigned(BigDecimal.valueOf(jBudget.getMoneyAssigned()));
      budget.setCurrent(BigDecimal.valueOf(jBudget.getMoneyAvailable()));
      budget.setCycle(jBudget.getCycle());
      budget.setCreatedOn(jBudget.getCreatedOn());
      return budget;
  }

  public static List<JCategoryBudget> with(
          List<JCategory> jCategories, JBudget jbudget) {

    return jCategories.stream()
        .map(
            jCategory -> {
              var jCategoryBudget = new JCategoryBudget();
              jCategoryBudget.setCategoryId(jCategory.getId());
              jCategoryBudget.setBudgetId(jbudget.getId());
              jCategoryBudget.setUserDefined(Boolean.FALSE);
              jCategoryBudget.setAllocated(0.0);
              jCategoryBudget.setUsed(0.0);
              jCategoryBudget.setAutoDeduct(Boolean.TRUE);
              jCategoryBudget.setCreatedOn(LocalDateTime.now());
              return jCategoryBudget;
            })
        .collect(Collectors.toList());
  }

}
