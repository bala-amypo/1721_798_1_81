package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;

public class DisposalRecordServiceImpl {

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

    public DisposalRecord createDisposal(Long assetId, DisposalRecord dr) {
        Asset asset = assetRepo.findById(assetId).orElseThrow();
        asset.setStatus("DISPOSED");
        dr.setAsset(asset);
        dr.prePersist();
        return repo.save(dr);
    }

    public DisposalRecord getDisposal(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal record not found"));
    }
}
