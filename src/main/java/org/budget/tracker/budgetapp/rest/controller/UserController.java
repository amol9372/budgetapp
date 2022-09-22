package org.budget.tracker.budgetapp.rest.controller;

import org.budget.tracker.budgetapp.app.User;
import org.budget.tracker.budgetapp.rest.request.CreateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Validated CreateUserRequest request) {
        User user = new User();
        user.setEmail("amolsingh@gmail.com");
        user.setFirstName("amol");
        return ResponseEntity.status(201).body(user);
    }

}
