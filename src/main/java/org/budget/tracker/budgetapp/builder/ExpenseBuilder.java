package org.budget.tracker.budgetapp.builder;

import org.budget.tracker.budgetapp.db.JExpense;
import org.budget.tracker.budgetapp.db.JGroup;
import org.budget.tracker.budgetapp.rest.request.CreateExpenseRequest;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Objects;

public class ExpenseBuilder {

    public static JExpense with(CreateExpenseRequest request, @Nullable JGroup group) {

        var expense = new JExpense();
        expense.setName(request.getName());
        expense.setCost(Float.parseFloat(request.getCost()));
        if (Objects.nonNull(group)) {
            expense.setGroupId(group.getId());
        }
        expense.setCategory(request.getCategory());
        expense.setPaidBy(request.getPaidBy());
        expense.setCreatedBy(request.getCreatedBy());
        expense.setCreatedOn(LocalDateTime.now());
        expense.setUpdatedOn(LocalDateTime.now());

        return expense;
    }

}
