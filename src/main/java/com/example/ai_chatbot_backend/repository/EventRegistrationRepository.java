package com.example.ai_chatbot_backend.repository;

import com.example.ai_chatbot_backend.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    // ✅ ADD THIS:
    List<EventRegistration> findByEventId(Long eventId);
}