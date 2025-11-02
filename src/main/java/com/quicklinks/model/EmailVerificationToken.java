package com.quicklinks.model;

import java.sql.Timestamp;

public class EmailVerificationToken {
    private int id;
    private int userId;
    private String token;
    private Timestamp expiry;

    public EmailVerificationToken() {}

    public EmailVerificationToken(int id, int userId, String token, Timestamp expiry) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.expiry = expiry;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public boolean isExpired() {
        return expiry.before(new java.sql.Timestamp(System.currentTimeMillis()));
    }
}