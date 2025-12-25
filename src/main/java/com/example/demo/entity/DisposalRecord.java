package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DisposalRecord {
    private Long id;
    private Asset asset;
    private String disposalMethod;
    private LocalDate disposalDate;
    private User approvedBy;
    private String notes;
    private LocalDateTime createdAt;

    public DisposalRecord() {}

    public DisposalRecord(Long id, Asset asset, String method,
                          LocalDate date, User approvedBy,
                          String notes, LocalDateTime createdAt) {
        this.id = id;
        this.asset = asset;
        this.disposalMethod = method;
        this.disposalDate = date;
        this.approvedBy = approvedBy;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getApprovedBy() { return approvedBy; }
}
