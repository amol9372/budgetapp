package org.budget.tracker.budgetapp.repository;

import org.budget.tracker.budgetapp.db.JGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupJpaRepository extends JpaRepository<JGroup, Integer> {
    JGroup findByName(String name);
}
