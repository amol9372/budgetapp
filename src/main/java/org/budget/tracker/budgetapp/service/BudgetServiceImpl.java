package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.app.Budget;
import org.budget.tracker.budgetapp.builder.BudgetBuilder;
import org.budget.tracker.budgetapp.db.JBudget;
import org.budget.tracker.budgetapp.repository.BudgetJpaRepository;
import org.budget.tracker.budgetapp.rest.request.CreateBudgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {



  @Autowired BudgetJpaRepository budgetJpaRepository;

  @Override
  public void createBudget(CreateBudgetRequest request) {

    JBudget jBudget = BudgetBuilder.with(request);

    budgetJpaRepository.save(jBudget);


  }
}
