package com.example.ai_chatbot_backend.controller;

import com.example.ai_chatbot_backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<?> getChatResponse(@RequestBody Map<String, String> request) {
        try {
            // 🚀 NO authentication check needed here

            // 1. Validate input
            String message = request.get("message");
            if (message == null || message.trim().isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of(
                                "error", "Message cannot be empty"
                        ));
            }

            // 2. Get AI response
            String responseMessage = chatService.getChatResponse(message);

            // 3. Return success response
            return ResponseEntity.ok(Map.of(
                    "message", responseMessage,
                    "timestamp", System.currentTimeMillis()
            ));

        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of(
                            "error", "Failed to process message: " + e.getMessage()
                    ));
        }
    }
}
