package com.example.demo.repository;

import com.example.demo.entity.Asset;
import java.util.List;

public interface AssetRepository {
    Asset save(Asset asset);
    List<Asset> findAll();
    java.util.Optional<Asset> findById(Long id);
    List<Asset> findByStatus(String status);
}
