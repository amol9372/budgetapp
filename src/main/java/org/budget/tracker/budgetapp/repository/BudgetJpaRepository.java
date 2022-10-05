package org.budget.tracker.budgetapp.repository;

import org.budget.tracker.budgetapp.db.JBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetJpaRepository extends JpaRepository<JBudget, Integer> {}
