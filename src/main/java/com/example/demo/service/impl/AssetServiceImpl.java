package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;

import java.util.List;

public class AssetServiceImpl {

    private final AssetRepository repo;

    public AssetServiceImpl(AssetRepository repo) {
        this.repo = repo;
    }

    public Asset createAsset(Asset asset) {
        asset.prePersist();
        return repo.save(asset);
    }

    public Asset getAsset(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }

    public List<Asset> getAllAssets() {
        return repo.findAll();
    }

    public Asset updateStatus(Long id, String status) {
        Asset a = getAsset(id);
        a.setStatus(status);
        return repo.save(a);
    }
}
