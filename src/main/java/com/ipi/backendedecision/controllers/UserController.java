package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.exceptions.LoginFailedException;
import com.ipi.backendedecision.models.User;
import com.ipi.backendedecision.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword())
                .orElseThrow(LoginFailedException::new);
    }

    @GetMapping("/users")
    public List<User> all() {
        return (List<User>) userRepository.findAll();
    }

}
