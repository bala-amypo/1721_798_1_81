package com.example.demo.service.impl;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import org.springframework.stereotype.Service;

@Service
public class TransferRecordServiceImpl implements TransferRecordService {

    @Override
    public TransferRecord transferAsset(TransferRecord record) {
        return record;
    }
}
