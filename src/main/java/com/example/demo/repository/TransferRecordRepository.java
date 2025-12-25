package com.example.demo.repository;

import com.example.demo.entity.TransferRecord;

import java.util.List;

public interface TransferRecordRepository {
    TransferRecord save(TransferRecord record);
    List<TransferRecord> findByAsset_Id(Long assetId);
}
