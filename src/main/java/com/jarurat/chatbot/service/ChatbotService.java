package com.jarurat.chatbot.service;

import com.jarurat.chatbot.model.IncomingMessage;
import com.jarurat.chatbot.model.OutgoingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Core chatbot service.
 * Handles message logging and generates predefined replies
 * based on the incoming message body.
 */
@Service
public class ChatbotService {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);

    private static final Map<String, String> REPLY_MAP = new HashMap<>();

    static {
        REPLY_MAP.put("hi",        "Hello! How can I assist you today?");
        REPLY_MAP.put("hello",     "Hello! How can I assist you today?");
        REPLY_MAP.put("bye",       "Goodbye! Have a wonderful day.");
        REPLY_MAP.put("goodbye",   "Goodbye! Have a wonderful day.");
        REPLY_MAP.put("help",      "Sure! You can say: Hi, Hello, Bye, Goodbye, Thanks.");
        REPLY_MAP.put("thanks",    "You're welcome! Is there anything else I can help with?");
        REPLY_MAP.put("thank you", "You're welcome! Is there anything else I can help with?");
    }

    /**
     * Processes an incoming message: logs it and returns an appropriate reply.
     */
    public OutgoingMessage processMessage(IncomingMessage message) {
        logger.info("📩 Incoming | From: {} | Body: \"{}\" | Time: {}",
                message.getFrom(),
                message.getBody(),
                message.getTimestamp() != null ? message.getTimestamp() : "N/A");

        String replyText = generateReply(message.getBody());

        logger.info("📤 Outgoing | To: {} | Reply: \"{}\"", message.getFrom(), replyText);

        return new OutgoingMessage(message.getFrom(), replyText, "sent");
    }

    /**
     * Returns a predefined reply based on trimmed, lowercased message body.
     */
    private String generateReply(String body) {
        if (body == null || body.isBlank()) {
            return "I received an empty message. Please send a valid message.";
        }
        String normalised = body.trim().toLowerCase();
        return REPLY_MAP.getOrDefault(
                normalised,
                "I'm sorry, I didn't understand that. Type \"Help\" to see available commands."
        );
    }
}
