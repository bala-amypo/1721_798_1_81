package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.TransferRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.service.TransferRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRecordServiceImpl implements TransferRecordService {

    private final TransferRecordRepository transferRepo;
    private final AssetRepository assetRepo;

    public TransferRecordServiceImpl(
            TransferRecordRepository transferRepo,
            AssetRepository assetRepo) {
        this.transferRepo = transferRepo;
        this.assetRepo = assetRepo;
    }

    @Override
    public TransferRecord create(Long assetId, TransferRecord record) {
        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        record.setAsset(asset);
        return transferRepo.save(record);
    }

    @Override
    public List<TransferRecord> getByAsset(Long assetId) {
        return transferRepo.findByAssetId(assetId);
    }

    @Override
    public TransferRecord getById(Long id) {
        return transferRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer record not found"));
    }
}
