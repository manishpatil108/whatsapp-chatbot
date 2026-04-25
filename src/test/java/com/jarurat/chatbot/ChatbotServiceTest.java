package com.jarurat.chatbot;

import com.jarurat.chatbot.model.IncomingMessage;
import com.jarurat.chatbot.model.OutgoingMessage;
import com.jarurat.chatbot.service.ChatbotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatbotServiceTest {

    @Autowired
    private ChatbotService chatbotService;

    @Test
    void testHiReply() {
        IncomingMessage msg = new IncomingMessage("+919876543210", "Hi", "2024-04-23T10:00:00Z");
        OutgoingMessage reply = chatbotService.processMessage(msg);
        assertEquals("Hello! How can I assist you today?", reply.getReply());
        assertEquals("sent", reply.getStatus());
    }

    @Test
    void testByeReply() {
        IncomingMessage msg = new IncomingMessage("+919876543210", "Bye", null);
        OutgoingMessage reply = chatbotService.processMessage(msg);
        assertEquals("Goodbye! Have a wonderful day.", reply.getReply());
    }

    @Test
    void testCaseInsensitive() {
        IncomingMessage msg = new IncomingMessage("+919876543210", "HI", null);
        OutgoingMessage reply = chatbotService.processMessage(msg);
        assertEquals("Hello! How can I assist you today?", reply.getReply());
    }

    @Test
    void testUnknownMessage() {
        IncomingMessage msg = new IncomingMessage("+919876543210", "random text", null);
        OutgoingMessage reply = chatbotService.processMessage(msg);
        assertTrue(reply.getReply().contains("didn't understand"));
    }

    @Test
    void testHelpReply() {
        IncomingMessage msg = new IncomingMessage("+919876543210", "Help", null);
        OutgoingMessage reply = chatbotService.processMessage(msg);
        assertTrue(reply.getReply().contains("Hi"));
    }
}
