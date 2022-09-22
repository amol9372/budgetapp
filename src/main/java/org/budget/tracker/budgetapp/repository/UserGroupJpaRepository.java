package org.budget.tracker.budgetapp.repository;

import org.budget.tracker.budgetapp.db.JUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupJpaRepository extends JpaRepository<JUserGroup, Integer> {


}
