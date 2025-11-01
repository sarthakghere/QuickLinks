package com.quicklinks.model;

import java.sql.Timestamp;

public class ShortLink {
    private int id;
    private int userId;
    private String originalUrl;
    private String shortCode;
    private Timestamp createdAt;
    private int totalVisits;

    public ShortLink() {}

    public ShortLink(int id, int userId, String originalUrl, String shortCode, Timestamp createdAt, int totalVisits) {
        this.id = id;
        this.userId = userId;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.createdAt = createdAt;
        this.totalVisits = totalVisits;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }

    public String getShortCode() { return shortCode; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public int getTotalVisits() { return totalVisits; }
    public void setTotalVisits(int totalVisits) { this.totalVisits = totalVisits; }
}
