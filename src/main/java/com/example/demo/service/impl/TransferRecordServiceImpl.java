package com.example.demo.service.impl;
import com.example.demo.entity.Asset;
import com.example.demo.entity.TransferRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.service.TransferRecordService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransferRecordServiceImpl implements TransferRecordService {

    private final TransferRecordRepository repo;
    private final AssetRepository assetRepo;

    public TransferRecordServiceImpl(
            TransferRecordRepository repo,
            AssetRepository assetRepo) {
        this.repo = repo;
        this.assetRepo = assetRepo;
    }

    @Override
    public TransferRecord createTransfer(Long assetId, TransferRecord record) {

        if (record.getTransferDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Transfer date cannot be future");
        }

        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        asset.setStatus("TRANSFERRED");
        record.setAsset(asset);

        return repo.save(record);
    }
}
