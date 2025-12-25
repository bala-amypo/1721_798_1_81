package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.*;
import com.example.demo.service.TransferRecordService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransferRecordServiceImpl implements TransferRecordService {

    private final TransferRecordRepository transferRecordRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public TransferRecordServiceImpl(
            TransferRecordRepository transferRecordRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {
        this.transferRecordRepository = transferRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public TransferRecord createTransfer(Long assetId, TransferRecord record) {
        if (record.getTransferDate().isAfter(LocalDate.now()))
            throw new ValidationException("Transfer date cannot be in the future");

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        record.setAsset(asset);
        return transferRecordRepository.save(record);
    }

    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        return transferRecordRepository.findByAsset_Id(assetId);
    }

    public TransferRecord getTransfer(Long id) {
        return transferRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));
    }
}
