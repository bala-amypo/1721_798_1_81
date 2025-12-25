package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repo;

    public AssetServiceImpl(AssetRepository repo) {
        this.repo = repo;
    }

    public Asset createAsset(Asset asset) {
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
        Asset asset = getAsset(id);
        asset.setStatus(status);
        return repo.save(asset);
    }
}
