package com.demo.model;

public class Twit {
    private String username;
    private String details;

    public Twit(String username, String details) {
        this.username = username;
        this.details = details;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
