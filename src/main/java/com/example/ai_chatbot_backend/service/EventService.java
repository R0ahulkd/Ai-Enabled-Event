package com.example.ai_chatbot_backend.service;

import com.example.ai_chatbot_backend.model.Event;
import com.example.ai_chatbot_backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    public Event create(Event event) {
        return repository.save(event);
    }

    public List<Event> getAll() {
        return repository.findAll();
    }

    public Event getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}