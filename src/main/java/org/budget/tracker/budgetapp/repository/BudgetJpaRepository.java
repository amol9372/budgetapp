package org.budget.tracker.budgetapp.repository;

import org.budget.tracker.budgetapp.db.JBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetJpaRepository extends JpaRepository<JBudget, Integer> {
    List<JBudget> findByUserId(Integer userId);
}
