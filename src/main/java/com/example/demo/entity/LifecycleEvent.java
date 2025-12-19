package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LifecycleEvent {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Asset asset;

    private String eventType;
    private String eventDescription;
    private LocalDateTime eventDate;

    @ManyToOne
    private User performedBy;

    @PrePersist
    void init() {
        eventDate = LocalDateTime.now();
    }
}
