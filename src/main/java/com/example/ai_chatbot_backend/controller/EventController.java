package com.example.ai_chatbot_backend.controller;

import com.example.ai_chatbot_backend.model.Event;
import com.example.ai_chatbot_backend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.create(event); // Use the correct service method name
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAll(); // Use the correct service method name
        return ResponseEntity.ok(events);
    }
}