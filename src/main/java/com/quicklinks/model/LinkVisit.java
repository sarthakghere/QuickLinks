package com.quicklinks.model;

import java.sql.Timestamp;

public class LinkVisit {
    private int id;
    private int linkId;
    private Timestamp visitedAt;

    public LinkVisit() {}

    public LinkVisit(int id, int linkId, Timestamp visitedAt) {
        this.id = id;
        this.linkId = linkId;
        this.visitedAt = visitedAt;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getLinkId() { return linkId; }
    public void setLinkId(int linkId) { this.linkId = linkId; }

    public Timestamp getVisitedAt() { return visitedAt; }
    public void setVisitedAt(Timestamp visitedAt) { this.visitedAt = visitedAt; }
}
