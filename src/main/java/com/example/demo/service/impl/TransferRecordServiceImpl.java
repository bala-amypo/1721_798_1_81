package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.*;

import java.time.LocalDate;
import java.util.List;

public class TransferRecordServiceImpl {

    private final TransferRecordRepository repo;
    private final AssetRepository assetRepo;
    private final UserRepository userRepo;

    public TransferRecordServiceImpl(TransferRecordRepository repo,
                                     AssetRepository assetRepo,
                                     UserRepository userRepo) {
        this.repo = repo;
        this.assetRepo = assetRepo;
        this.userRepo = userRepo;
    }

    public TransferRecord createTransfer(Long assetId, TransferRecord tr) {
        if (tr.getTransferDate().isAfter(LocalDate.now()))
            throw new ValidationException("Transfer date cannot be in the future");

        Asset asset = assetRepo.findById(assetId).orElseThrow();
        tr.setAsset(asset);
        return repo.save(tr);
    }

    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        return repo.findByAsset_Id(assetId);
    }
}
