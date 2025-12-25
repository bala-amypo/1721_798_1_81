package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Asset {
    private Long id;
    private String assetTag;
    private String category;
    private String brand;
    private LocalDate purchaseDate;
    private String status;
    private User currentHolder;
    private LocalDateTime createdAt;

    public Asset() {}

    public Asset(Long id, String assetTag, String category, String brand,
                 LocalDate purchaseDate, String status,
                 User currentHolder, LocalDateTime createdAt) {
        this.id = id;
        this.assetTag = assetTag;
        this.category = category;
        this.brand = brand;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.currentHolder = currentHolder;
        this.createdAt = createdAt;
    }

    public void prePersist() {
        if (status == null) status = "AVAILABLE";
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAssetTag() { return assetTag; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public User getCurrentHolder() { return currentHolder; }
}
