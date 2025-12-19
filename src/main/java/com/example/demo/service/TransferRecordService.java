package com.example.demo.service;

import com.example.demo.entity.TransferRecord;

import java.util.List;

public interface TransferRecordService {

    TransferRecord create(Long assetId, TransferRecord record);

    List<TransferRecord> getByAsset(Long assetId);

    TransferRecord getById(Long id);
}
