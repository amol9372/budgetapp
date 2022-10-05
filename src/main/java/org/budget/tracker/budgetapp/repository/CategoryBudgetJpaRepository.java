package org.budget.tracker.budgetapp.repository;

import org.budget.tracker.budgetapp.db.JCategoryBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryBudgetJpaRepository extends JpaRepository<JCategoryBudget, Integer> {}
