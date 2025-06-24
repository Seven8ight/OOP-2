package com.test.oop2.controller;

import com.test.oop2.model.Role;
import com.test.oop2.model.User;
import com.test.oop2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepo userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(404).body("Email already exists");
        }

        // ✅ Explicitly assign a new UUID to the user
        user.setId(UUID.randomUUID());
        user.setRole(Role.CUSTOMER);

        User savedUser = userRepository.save(user);

        // ✅ Sanitize before returning
        savedUser.setPassword(null);

        return ResponseEntity.ok(savedUser);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());

        if (foundUser.isEmpty()) {
            return ResponseEntity.status(404).body("User not found.");
        }

        User dbUser = foundUser.get();

        if (!dbUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body("Incorrect password.");
        }

        // Hide sensitive info
        dbUser.setPassword(null); // Don't expose the password in the response

        return ResponseEntity.ok(dbUser); // ✅ Full user object including role
    }

}
