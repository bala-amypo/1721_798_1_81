package com.example.demo.service;

import com.example.demo.entity.TransferRecord;

public interface TransferRecordService {
    TransferRecord createTransfer(Long assetId, TransferRecord record);
}
