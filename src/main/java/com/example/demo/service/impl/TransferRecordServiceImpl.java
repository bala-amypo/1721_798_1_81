package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferRecordService;
import java.time.LocalDate;
import java.util.List;

public class TransferRecordServiceImpl implements TransferRecordService {

    private final TransferRecordRepository repo;
    private final AssetRepository assetRepo;
    private final UserRepository userRepo;

    public TransferRecordServiceImpl(TransferRecordRepository repo,
                                     AssetRepository assetRepo,
                                     UserRepository userRepo) {
        this.repo = repo;
        this.assetRepo = assetRepo;
        this.userRepo = userRepo;
    }

    public TransferRecord createTransfer(Long assetId, TransferRecord record) {
        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        User approver = userRepo.findById(record.getApprovedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!"ADMIN".equals(approver.getRole())) {
            throw new ValidationException("Approver must be ADMIN");
        }
        if (record.getFromDepartment().equals(record.getToDepartment())) {
            throw new ValidationException("Departments must differ");
        }
        if (record.getTransferDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Transfer date cannot be in the future");
        }

        record.setAsset(asset);
        record.setApprovedBy(approver);
        return repo.save(record);
    }

    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        return repo.findByAssetId(assetId);
    }

    public TransferRecord getTransfer(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));
    }
}
