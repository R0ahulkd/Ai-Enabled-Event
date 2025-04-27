package com.example.ai_chatbot_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ChatService {

    private final RestTemplate restTemplate;
    private static final String FASTAPI_URL = "http://localhost:8000/chat";
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    public ChatService() {
        this.restTemplate = new RestTemplate();
    }

    public String getChatResponse(String userMessage) {
        try {
            if (userMessage == null || userMessage.trim().isEmpty()) {
                return "❗Error: Message cannot be empty.";
            }

            String encodedMessage = URLEncoder.encode(userMessage, StandardCharsets.UTF_8);
            String apiUrl = FASTAPI_URL + "?query=" + encodedMessage;
            logger.info("Sending query to FastAPI: {}", apiUrl);

            ResponseEntity<Map> response = restTemplate.getForEntity(apiUrl, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                logger.info("FastAPI Response: {}", responseBody);

                if (responseBody.containsKey("bot_response")) {
                    return responseBody.get("bot_response").toString();
                } else {
                    logger.error("Unexpected response format from FastAPI: {}", responseBody);
                    return "❓ Sorry, invalid AI response.";
                }
            } else {
                logger.error("Non-OK status from FastAPI: {}", response.getStatusCode());
                return "❗Error: FastAPI returned bad status.";
            }

        } catch (RestClientException e) {
            logger.error("RestClientException while connecting to FastAPI:", e);
            return "❗Error: Failed to connect to AI backend.";
        } catch (Exception e) {
            logger.error("Exception in getChatResponse:", e);
            return "❗Error: An unexpected error occurred.";
        }
    }
}
