package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DisposalRecord {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Asset asset;

    private String disposalMethod;
    private LocalDate disposalDate;

    @ManyToOne
    private User approvedBy;

    private String notes;
    private LocalDateTime createdAt;

    @PrePersist
    void init() {
        createdAt = LocalDateTime.now();
    }
}
