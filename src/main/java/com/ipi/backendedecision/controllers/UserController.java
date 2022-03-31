package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.exceptions.LoginFailedException;
import com.ipi.backendedecision.exceptions.UserNotFoundException;
import com.ipi.backendedecision.models.User;
import com.ipi.backendedecision.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User newUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
               });
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
