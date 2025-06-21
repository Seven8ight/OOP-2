package com.test.oop2.controller;

import com.test.oop2.model.User;
import com.test.oop2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepo userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            return "Email already registered.";
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            return "Username already taken.";

        userRepository.save(user);
        return "User registered successfully.";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isEmpty())
            return "User not found.";

        if (!foundUser.get().getPassword().equals(user.getPassword()))
            return "Incorrect password.";

        return "Login successful.";
    }
}
