package com.example.demo.entity;

import java.time.LocalDate;

public class TransferRecord {
    private Long id;
    private Asset asset;
    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public void setAsset(Asset asset) { this.asset = asset; }
    public void setApprovedBy(User user) { this.approvedBy = user; }
    public User getApprovedBy() { return approvedBy; }
    public String getFromDepartment() { return fromDepartment; }
    public String getToDepartment() { return toDepartment; }
    public LocalDate getTransferDate() { return transferDate; }
    public void setTransferDate(LocalDate date) { this.transferDate = date; }
}
