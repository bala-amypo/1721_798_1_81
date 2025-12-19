package com.example.demo.service;

import com.example.demo.entity.DisposalRecord;

import java.util.List;

public interface DisposalRecordService {

    DisposalRecord create(Long assetId, DisposalRecord record);

    List<DisposalRecord> getAll();

    DisposalRecord getById(Long id);
}
