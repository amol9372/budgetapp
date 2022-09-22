package org.budget.tracker.budgetapp.repository;

import org.budget.tracker.budgetapp.db.JUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersJpaRepository extends JpaRepository<JUsers, Integer> {

    Optional<JUsers> findByEmail(String email);
}
