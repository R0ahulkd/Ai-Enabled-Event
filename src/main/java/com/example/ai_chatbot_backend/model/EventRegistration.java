package com.example.ai_chatbot_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EventRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;  // Event ID
    private String userEmail;  // Who is registering (you can get from frontend or token)
}
