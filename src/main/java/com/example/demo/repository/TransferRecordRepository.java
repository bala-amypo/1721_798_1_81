package com.example.demo.repository;

import com.example.demo.entity.TransferRecord;
import java.util.*;

public interface TransferRecordRepository {
    TransferRecord save(TransferRecord record);
    Optional<TransferRecord> findById(Long id);
    List<TransferRecord> findByAsset_Id(Long assetId);
}
