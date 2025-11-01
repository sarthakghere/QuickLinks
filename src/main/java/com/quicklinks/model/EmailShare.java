package com.quicklinks.model;

import java.sql.Timestamp;

public class EmailShare {
    private int id;
    private int linkId;
    private int senderId;
    private String recipientEmail;
    private Timestamp sentAt;

    public EmailShare() {}

    public EmailShare(int id, int linkId, int senderId, String recipientEmail, Timestamp sentAt) {
        this.id = id;
        this.linkId = linkId;
        this.senderId = senderId;
        this.recipientEmail = recipientEmail;
        this.sentAt = sentAt;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getLinkId() { return linkId; }
    public void setLinkId(int linkId) { this.linkId = linkId; }

    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }

    public String getRecipientEmail() { return recipientEmail; }
    public void setRecipientEmail(String recipientEmail) { this.recipientEmail = recipientEmail; }

    public Timestamp getSentAt() { return sentAt; }
    public void setSentAt(Timestamp sentAt) { this.sentAt = sentAt; }
}
