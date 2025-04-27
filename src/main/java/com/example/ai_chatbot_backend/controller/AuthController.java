package com.example.ai_chatbot_backend.controller;

import com.example.ai_chatbot_backend.model.User;
import com.example.ai_chatbot_backend.repository.UserRepository;
import com.example.ai_chatbot_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Validate email exists
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity
                        .status(409)
                        .body(Map.of(
                                "error", "Email already registered"
                        ));
            }

            // Validate password
            if (user.getPassword() == null || user.getPassword().length() < 6) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of(
                                "error", "Password must be at least 6 characters"
                        ));
            }

            // Hash password and save user
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);

            // Generate token
            String token = jwtUtil.generateToken(savedUser.getEmail(), "USER");

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "user", Map.of(
                            "id", savedUser.getId(),
                            "email", savedUser.getEmail(),
                            "name", savedUser.getName()
                    )
            ));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of(
                            "error", "Registration failed: " + e.getMessage()
                    ));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        try {
            Optional<User> foundUser = userRepository.findByEmail(loginRequest.getEmail());

            if (foundUser.isEmpty() || !passwordEncoder.matches(
                    loginRequest.getPassword(),
                    foundUser.get().getPassword()
            )) {
                return ResponseEntity
                        .status(401)
                        .body(Map.of(
                                "error", "Invalid email or password"
                        ));
            }

            User user = foundUser.get();
            String token = jwtUtil.generateToken(user.getEmail(), "USER");

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "user", Map.of(
                            "email", user.getEmail(),
                            "name", user.getName()
                    )
            ));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of(
                            "error", "Login failed: " + e.getMessage()
                    ));
        }
    }
}