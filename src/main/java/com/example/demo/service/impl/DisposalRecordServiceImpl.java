package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.DisposalRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DisposalRecordServiceImpl implements DisposalRecordService {

    private final DisposalRecordRepository disposalRecordRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public DisposalRecordServiceImpl(
            DisposalRecordRepository disposalRecordRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {
        this.disposalRecordRepository = disposalRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public DisposalRecord createDisposal(Long assetId, DisposalRecord disposal) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        disposal.setAsset(asset);
        return disposalRecordRepository.save(disposal);
    }

    public DisposalRecord getDisposal(Long id) {
        return disposalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal record not found"));
    }

    public List<DisposalRecord> getAllDisposals() {
        return disposalRecordRepository.findAll();
    }
}
