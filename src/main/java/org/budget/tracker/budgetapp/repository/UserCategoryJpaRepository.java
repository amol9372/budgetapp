package org.budget.tracker.budgetapp.repository;

import org.budget.tracker.budgetapp.db.JUserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoryJpaRepository extends JpaRepository<JUserCategory, Integer> {

  JUserCategory findByNameAndUserId(String category, Integer userId);
}
