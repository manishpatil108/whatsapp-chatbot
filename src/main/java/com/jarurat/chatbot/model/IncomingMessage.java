package com.jarurat.chatbot.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents an incoming WhatsApp-simulated message payload.
 */
public class IncomingMessage {

    @NotBlank(message = "Sender phone number must not be blank")
    private String from;

    @NotBlank(message = "Message body must not be blank")
    private String body;

    private String timestamp;

    public IncomingMessage() {}

    public IncomingMessage(String from, String body, String timestamp) {
        this.from = from;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "IncomingMessage{from='" + from + "', body='" + body + "', timestamp='" + timestamp + "'}";
    }
}
