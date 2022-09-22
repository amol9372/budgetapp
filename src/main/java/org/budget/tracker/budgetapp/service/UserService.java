package org.budget.tracker.budgetapp.service;

import org.budget.tracker.budgetapp.rest.request.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void createUser(CreateUserRequest request);

}
