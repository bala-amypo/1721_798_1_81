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

    public DisposalRecord(Long id, Asset asset, String disposalMethod,
                          LocalDate disposalDate, User approvedBy,
                          String notes, LocalDateTime createdAt) {
        this.id = id;
        this.asset = asset;
        this.disposalMethod = disposalMethod;
        this.disposalDate = disposalDate;
        this.approvedBy = approvedBy;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    // getters & setters
}
