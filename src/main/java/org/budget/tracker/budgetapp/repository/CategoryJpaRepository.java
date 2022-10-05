package org.budget.tracker.budgetapp.repository;

import org.budget.tracker.budgetapp.db.JCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository extends JpaRepository<JCategory, Integer> {

  JCategory findByName(String category);
}
