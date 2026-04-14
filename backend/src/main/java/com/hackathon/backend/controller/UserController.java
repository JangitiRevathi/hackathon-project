package com.hackathon.backend.controller;

import com.hackathon.backend.model.User;
import com.hackathon.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUserById(id);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existingUser = service.login(user.getEmail(), user.getPassword());

        if (existingUser != null) {
            return "Login Successful";
        } else {
            return "Invalid Email or Password";
        }
    }
}