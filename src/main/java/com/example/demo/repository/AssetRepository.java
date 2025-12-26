package com.example.demo.repository;

import com.example.demo.entity.Asset;
import java.util.*;

public interface AssetRepository {
    Asset save(Asset asset);
    Optional<Asset> findById(Long id);
    List<Asset> findAll();
    List<Asset> findByStatus(String status);
}
