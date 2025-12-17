package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class LifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Asset asset;

    private String eventType;
    private String eventDescription;

    private LocalDateTime eventDate;

    @ManyToOne
    private User performedBy;

    @PrePersist
    public void prePersist() {
        eventDate = LocalDateTime.now();
    }

    // getters & setters
}
