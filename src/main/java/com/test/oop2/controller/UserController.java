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
        System.out.println(user);

        // Default role can be CUSTOMER
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
        return ResponseEntity.ok(user);
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

    @GetMapping("/user/{email}") // Maps to /api/users/email/{email}
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        // Use Optional to handle the case where the user might not be found
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // IMPORTANT: Nullify the password before returning the user object
            // This prevents sensitive data from being exposed via the API.
            user.setPassword(null);
            return ResponseEntity.ok(user); // Return 200 OK with the user object
        } else {
            // If user is not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOptional.get();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getBalance() != null) {
            user.setBalance(updatedUser.getBalance());
        }
        userRepository.save(user);
        user.setPassword(null); // Very important
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}") // Maps to /api/users/email/{email}
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        // Use Optional to handle the case where the user might not be found
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // IMPORTANT: Nullify the password before returning the user object
            // This prevents sensitive data from being exposed via the API.
            user.setPassword(null);
            return ResponseEntity.ok(user); // Return 200 OK with the user object
        } else {
            // If user is not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

}
