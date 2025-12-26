package com.example.demo.repository;

import com.example.demo.entity.DisposalRecord;
import java.util.*;

public interface DisposalRecordRepository {
    DisposalRecord save(DisposalRecord record);
    Optional<DisposalRecord> findById(Long id);
    List<DisposalRecord> findAll();
}
