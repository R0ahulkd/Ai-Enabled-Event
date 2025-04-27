package com.example.ai_chatbot_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;

    @Column(name = "date_time")
    private LocalDateTime eventDateTime;

    @Column(name = "available_seats") // ✅ Important mapping
    private int availableSeats; // ✅ Must be int, not Integer
}
