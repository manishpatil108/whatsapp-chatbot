package com.jarurat.chatbot.controller;

import com.jarurat.chatbot.model.IncomingMessage;
import com.jarurat.chatbot.model.OutgoingMessage;
import com.jarurat.chatbot.service.ChatbotService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller exposing the /webhook endpoint.
 * Simulates a WhatsApp webhook that receives inbound messages
 * and returns chatbot replies.
 */
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    private final ChatbotService chatbotService;

    public WebhookController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    /**
     * POST /webhook
     * Accepts a JSON body simulating a WhatsApp inbound message.
     *
     * Example:
     * {
     *   "from": "+919876543210",
     *   "body": "Hi",
     *   "timestamp": "2024-04-23T10:00:00Z"
     * }
     */
    @PostMapping
    public ResponseEntity<OutgoingMessage> receiveMessage(@Valid @RequestBody IncomingMessage message) {
        logger.info("🔔 POST /webhook called");
        OutgoingMessage response = chatbotService.processMessage(message);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /webhook
     * Health-check and webhook verification endpoint.
     */
    @GetMapping
    public ResponseEntity<Map<String, String>> verifyWebhook(
            @RequestParam(value = "hub.challenge", required = false) String challenge) {

        logger.info("✅ GET /webhook — health check");

        if (challenge != null) {
            return ResponseEntity.ok(Map.of("hub.challenge", challenge));
        }

        return ResponseEntity.ok(Map.of(
                "status", "running",
                "message", "WhatsApp Chatbot Backend is live!"
        ));
    }
}
