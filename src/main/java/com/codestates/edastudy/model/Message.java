package com.codestates.edastudy.model;

public class Message {

    private final String to;
    private final String message;

    public Message(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public String getTo() {
        return this.to;
    }

    public String getMessage() {
        return this.message;
    }

}
