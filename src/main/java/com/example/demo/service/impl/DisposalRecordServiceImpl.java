package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.DisposalRecordService;
import java.time.LocalDate;
import java.util.List;

public class DisposalRecordServiceImpl implements DisposalRecordService {

    private final DisposalRecordRepository repo;
    private final AssetRepository assetRepo;
    private final UserRepository userRepo;

    public DisposalRecordServiceImpl(DisposalRecordRepository repo,
                                     AssetRepository assetRepo,
                                     UserRepository userRepo) {
        this.repo = repo;
        this.assetRepo = assetRepo;
        this.userRepo = userRepo;
    }

    public DisposalRecord createDisposal(Long assetId, DisposalRecord disposal) {
        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        User approver = userRepo.findById(disposal.getApprovedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!"ADMIN".equals(approver.getRole())) {
            throw new ValidationException("Approver must be ADMIN");
        }
        if (disposal.getDisposalDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Future disposal date");
        }

        disposal.setAsset(asset);
        asset.setStatus("DISPOSED");
        assetRepo.save(asset);

        return repo.save(disposal);
    }

    public DisposalRecord getDisposal(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal record not found"));
    }

    public List<DisposalRecord> getAllDisposals() {
        return repo.findAll();
    }
}
