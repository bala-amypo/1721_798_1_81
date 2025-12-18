package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetTag;
    private String name;
    private String category;
    private String status;

    private LocalDate purchaseDate;

    @ManyToOne
    private User currentHolder;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.assetTag == null) {
            this.assetTag = "AST-" + System.currentTimeMillis();
        }
    }
}
