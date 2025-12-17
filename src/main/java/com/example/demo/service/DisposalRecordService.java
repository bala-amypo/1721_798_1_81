package com.example.demo.service;

import com.example.demo.entity.DisposalRecord;

public interface DisposalRecordService {
    DisposalRecord disposeAsset(Long assetId, DisposalRecord record);
}
