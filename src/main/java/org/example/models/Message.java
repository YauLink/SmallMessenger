package org.example.models;

public class Message {
    private String from;
    private String to;
    private String text;
    private String timestamp;

    public Message(String from, String to, String text, String timestamp) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

