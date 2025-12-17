package com.example.demo.service.impl;

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
}
