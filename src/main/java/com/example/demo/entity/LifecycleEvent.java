package com.example.demo.entity;

import java.time.LocalDateTime;

public class LifecycleEvent {

    private Long id;
    private Asset asset;
    private String eventType;
    private String eventDescription;
    private LocalDateTime eventDate;
    private User performedBy;

    public LifecycleEvent() {}

    public LifecycleEvent(Long id, Asset asset, String eventType,
                          String eventDescription, LocalDateTime eventDate,
                          User performedBy) {
        this.id = id;
        this.asset = asset;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.performedBy = performedBy;
    }

    public void prePersist() {
        if (eventDate == null) eventDate = LocalDateTime.now();
    }

    // getters & setters
}
