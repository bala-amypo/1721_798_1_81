package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DisposalRecord;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DisposalRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DisposalRecordService;

import java.time.LocalDate;
import java.util.List;

public class DisposalRecordServiceImpl implements DisposalRecordService {

    private final DisposalRecordRepository disposalRecordRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    // REQUIRED constructor order
    public DisposalRecordServiceImpl(
            DisposalRecordRepository disposalRecordRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {
        this.disposalRecordRepository = disposalRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DisposalRecord createDisposal(Long assetId,
                                         DisposalRecord record) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found"));

        User approver = userRepository
                .findById(record.getApprovedBy().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!"ADMIN".equals(approver.getRole())) {
            throw new ValidationException(
                    "Approver must be ADMIN");
        }

        if (record.getDisposalDate()
                .isAfter(LocalDate.now())) {
            throw new ValidationException(
                    "Future disposal date");
        }

        record.setAsset(asset);
        record.setApprovedBy(approver);
        record.prePersist();

        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return disposalRecordRepository.save(record);
    }

    @Override
    public DisposalRecord getDisposal(Long id) {
        return disposalRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Disposal record not found"));
    }

    @Override
    public List<DisposalRecord> getAllDisposals() {
        return disposalRecordRepository.findAll();
    }
}
