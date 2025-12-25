package com.example.demo.repository;

import com.example.demo.entity.DisposalRecord;

import java.util.Optional;

public interface DisposalRecordRepository {
    DisposalRecord save(DisposalRecord record);
    Optional<DisposalRecord> findById(Long id);
}
