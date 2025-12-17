package com.example.demo.service.impl;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.entity.Asset;
import com.example.demo.repository.DisposalRecordRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
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
        if (disposal.getDisposalDate().isAfter(LocalDate.now()))
            throw new ValidationException("Disposal date cannot be future");

        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        asset.setStatus("DISPOSED");
        disposal.setAsset(asset);

        return repo.save(disposal);
    }
}
