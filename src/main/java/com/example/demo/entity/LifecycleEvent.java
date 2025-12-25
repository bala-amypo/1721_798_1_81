package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lifecycle_events")
public class LifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;

    private String eventDescription;

    private LocalDateTime eventTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public Asset getAsset() {
        return asset;
    }

    // ===== SETTERS (REQUIRED BY TESTS) =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
