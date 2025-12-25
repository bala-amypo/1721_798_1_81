package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;

import java.util.List;

public class TransferRecordController {

    private final TransferRecordService service;

    public TransferRecordController(TransferRecordService service) {
        this.service = service;
    }

    public TransferRecord create(Long assetId, TransferRecord tr) {
        return service.createTransfer(assetId, tr);
    }

    public List<TransferRecord> getByAsset(Long assetId) {
        return service.getTransfersForAsset(assetId);
    }
}
