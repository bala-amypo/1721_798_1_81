package com.example.demo.service.impl;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.stereotype.Service;

@Service
public class DisposalRecordServiceImpl implements DisposalRecordService {

    @Override
    public DisposalRecord disposeAsset(DisposalRecord record) {
        return record;
    }
}
