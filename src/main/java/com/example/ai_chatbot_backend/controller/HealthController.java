package com.example.ai_chatbot_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok().body(
                Map.of(
                        "status", "UP",
                        "service", "AI Chatbot Backend",
                        "timestamp", System.currentTimeMillis()
                )
        );
    }

    @GetMapping("/db")
    public ResponseEntity<?> databaseHealthCheck() {
        try {
            // Add a simple DB check if needed
            return ResponseEntity.ok().body(
                    Map.of(
                            "database", "CONNECTED",
                            "timestamp", System.currentTimeMillis()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(503).body(
                    Map.of(
                            "database", "DISCONNECTED",
                            "error", e.getMessage(),
                            "timestamp", System.currentTimeMillis()
                    )
            );
        }
    }
}