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
        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.status(409).body("Email already exists"); // Still recommend 409
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print to console
            return ResponseEntity.status(500).body("Error checking existing email: " + e.getMessage());
        }

        try {
            user.setId(UUID.randomUUID());
            user.setRole(Role.CUSTOMER); // Ensure Role.CUSTOMER is valid and accessible

            User savedUser = userRepository.save(user);

            savedUser.setPassword(null);

            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            e.printStackTrace(); // Print to console
            return ResponseEntity.status(500).body("Error saving user: " + e.getMessage());
        }
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
