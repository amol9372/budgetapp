package org.budget.tracker.budgetapp.repository;

import org.budget.tracker.budgetapp.db.JCategory;
import org.budget.tracker.budgetapp.db.JUserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCategoryJpaRepository extends JpaRepository<JUserCategory, Integer> {

  JUserCategory findByNameAndUserId(String category, Integer userId);

    Optional<List<JUserCategory>> findByBudgetId(Integer budgetId);

    JCategory findByNameAndBudgetId(String category, Integer userId);
}
