package com.codestates.edastudy.model;

public class Message {

    private final String to;
    private final String message;
    private String job;

    public Message(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public Message(String to, String message, String job) {
        this.to = to;
        this.message = message;
        this.job = job;
    }

    public String getTo() {
        return this.to;
    }

    public String getMessage() {
        return this.message;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
