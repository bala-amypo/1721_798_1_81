package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
    }

    @Override
    public Asset updateAsset(Long id, Asset asset) {
        Asset existing = getAssetById(id);
        existing.setName(asset.getName());
        existing.setStatus(asset.getStatus());
        existing.setCategory(asset.getCategory());
        return assetRepository.save(existing);
    }

    @Override
    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}
