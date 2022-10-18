package org.budget.tracker.budgetapp.builder;

import org.budget.tracker.budgetapp.app.CategoryBudget;
import org.budget.tracker.budgetapp.db.JCategory;
import org.budget.tracker.budgetapp.db.JCategoryBudget;
import org.budget.tracker.budgetapp.db.JUserCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryBudgetBuilder {

  public static List<CategoryBudget> with(
      List<JCategoryBudget> jCategoryBudgets,
      List<JCategory> jCategories,
      Optional<List<JUserCategory>> jUserCategories) {

    var categoryMap =
        jCategories.stream().collect(Collectors.toMap(JCategory::getId, JCategory::getName));

    jUserCategories.ifPresent(
        jUserCategoryList ->
            jUserCategoryList.forEach(
                jUserCategory -> categoryMap.put(jUserCategory.getId(), jUserCategory.getName())));

    return jCategoryBudgets.stream()
        .map(
            jCategoryBudget -> {
              var categoryBudget = new CategoryBudget();
              categoryBudget.setId(jCategoryBudget.getId());
              categoryBudget.setAllocated(BigDecimal.valueOf(jCategoryBudget.getAllocated()));
              categoryBudget.setName(categoryMap.get(jCategoryBudget.getCategoryId()));
              categoryBudget.setBudgetId(jCategoryBudget.getBudgetId());
              categoryBudget.setUsed(BigDecimal.valueOf(jCategoryBudget.getUsed()));
              categoryBudget.setUserDefined(jCategoryBudget.getUserDefined());
              categoryBudget.setAutoDeduct(jCategoryBudget.getAutoDeduct());
              categoryBudget.setAutoDeductOn(jCategoryBudget.getAutoDeductOn());
              return categoryBudget;
            })
        .collect(Collectors.toList());
  }
}
