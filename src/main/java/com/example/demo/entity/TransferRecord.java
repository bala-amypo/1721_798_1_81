package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TransferRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Asset asset;

    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;

    @ManyToOne
    private User approvedBy;
}
