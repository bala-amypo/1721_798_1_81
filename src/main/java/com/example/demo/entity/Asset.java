package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Asset {

    private Long id;
    private String assetTag;
    private String assetType;
    private String model;
    private LocalDate purchaseDate;
    private String status;
    private User currentHolder;
    private LocalDateTime createdAt;

    public Asset() {}

    public Asset(Long id, String assetTag, String assetType, String model,
                 LocalDate purchaseDate, String status,
                 User currentHolder, LocalDateTime createdAt) {
        this.id = id;
        this.assetTag = assetTag;
        this.assetType = assetType;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.currentHolder = currentHolder;
        this.createdAt = createdAt;
    }

    public void prePersist() {
        if (status == null) status = "AVAILABLE";
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    // getters & setters (ALL REQUIRED)
}
