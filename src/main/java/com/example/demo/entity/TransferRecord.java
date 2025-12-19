package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transfer_records")
public class TransferRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Asset asset;

    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;

    @ManyToOne
    private User approvedBy;

    public TransferRecord() {}

    public TransferRecord(Long id, Asset asset, String fromDepartment,
                          String toDepartment, LocalDate transferDate,
                          User approvedBy) {
        this.id = id;
        this.asset = asset;
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.transferDate = transferDate;
        this.approvedBy = approvedBy;
    }

    public String getFromDepartment() { return fromDepartment; }

public String getToDepartment() { return toDepartment; }

public LocalDate getTransferDate() { return transferDate; }

public User getApprovedBy() { return approvedBy; }

public void setAsset(Asset asset) { this.asset = asset; }

public void setApprovedBy(User user) { this.approvedBy = user; }

}
