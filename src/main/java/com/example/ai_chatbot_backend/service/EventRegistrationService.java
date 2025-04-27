package com.example.ai_chatbot_backend.service;

import com.example.ai_chatbot_backend.model.EventRegistration;
import com.example.ai_chatbot_backend.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventRegistrationService {

    private final EventRegistrationRepository registrationRepository;

    @Autowired
    public EventRegistrationService(EventRegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // ✅ Register user for event
    public EventRegistration register(EventRegistration registration) {
        return registrationRepository.save(registration);
    }

    // ✅ Get all registrations by Event ID
    public List<EventRegistration> getRegistrationsByEventId(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    // ✅ Optionally: Find specific registration by ID
    public Optional<EventRegistration> getRegistrationById(Long registrationId) {
        return registrationRepository.findById(registrationId);
    }

    // ✅ Optionally: Delete a registration
    public void deleteRegistration(Long registrationId) {
        registrationRepository.deleteById(registrationId);
    }
}