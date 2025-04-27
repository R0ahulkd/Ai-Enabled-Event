package com.example.ai_chatbot_backend.controller;

import com.example.ai_chatbot_backend.model.EventRegistration;
import com.example.ai_chatbot_backend.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
public class EventRegistrationController {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerForEvent(@RequestBody EventRegistration registration) {
        EventRegistration savedRegistration = eventRegistrationRepository.save(registration);
        return ResponseEntity.ok(savedRegistration);
    }
}
