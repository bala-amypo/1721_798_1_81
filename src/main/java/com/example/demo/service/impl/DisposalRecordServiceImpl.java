package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DisposalRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DisposalRecordRepository;
import com.example.demo.service.DisposalRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisposalRecordServiceImpl implements DisposalRecordService {

    private final DisposalRecordRepository disposalRepo;
    private final AssetRepository assetRepo;

    public DisposalRecordServiceImpl(
            DisposalRecordRepository disposalRepo,
            AssetRepository assetRepo) {
        this.disposalRepo = disposalRepo;
        this.assetRepo = assetRepo;
    }

    @Override
    public DisposalRecord create(Long assetId, DisposalRecord record) {
        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        record.setAsset(asset);
        return disposalRepo.save(record);
    }

    @Override
    public List<DisposalRecord> getAll() {
        return disposalRepo.findAll();
    }

    @Override
    public DisposalRecord getById(Long id) {
        return disposalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal record not found"));
    }
}
