package com.example.demo.service.impl;

import com.example.demo.entity.TransferRecord;
import com.example.demo.entity.Asset;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.exception.ResourceNotFoundException;

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
}
