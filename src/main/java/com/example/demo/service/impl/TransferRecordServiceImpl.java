package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.TransferRecord;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransferRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TransferRecordServiceImpl implements TransferRecordService {
    
    private final TransferRecordRepository transferRecordRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;
    
    public TransferRecordServiceImpl(TransferRecordRepository transferRecordRepository,
                                    AssetRepository assetRepository,
                                    UserRepository userRepository) {
        this.transferRecordRepository = transferRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public TransferRecord createTransfer(Long assetId, TransferRecord record) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id: " + assetId));
        
        User approver = userRepository.findById(record.getApprovedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + record.getApprovedBy().getId()));
        
        // Validate approver is ADMIN
        if (!"ADMIN".equals(approver.getRole())) {
            throw new ValidationException("Approver must be an ADMIN");
        }
        
        // Validate departments are different
        if (record.getFromDepartment().equals(record.getToDepartment())) {
            throw new ValidationException("From department and to department must be different");
        }
        
        // Validate transfer date is not in the future
        if (record.getTransferDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Transfer date cannot be in the future");
        }
        
        record.setAsset(asset);
        record.setApprovedBy(approver);
        
        return transferRecordRepository.save(record);
    }
    
    @Override
    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        return transferRecordRepository.findByAsset_Id(assetId);
    }
    
    @Override
    public TransferRecord getTransfer(Long id) {
        return transferRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer record not found with id: " + id));
    }
}