package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
        this.eventDate = LocalDateTime.now();
    }
}
