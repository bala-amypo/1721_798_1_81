package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


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
