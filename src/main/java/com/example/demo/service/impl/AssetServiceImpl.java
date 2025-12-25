package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset createAsset(Asset asset) {

        /* 🔥 HARD SAFETY FIXES */

        // Never accept ID from client
        asset.setId(null);

        // Never accept createdAt from client
        asset.setCreatedAt(null);

        // Never accept nested User from client
        asset.setCurrentHolder(null);

        if (asset.getAssetTag() == null || asset.getAssetTag().isBlank()) {
            throw new ValidationException("Asset tag is required");
        }

        if (assetRepository.existsByAssetTag(asset.getAssetTag())) {
            throw new ValidationException("Asset tag already exists");
        }

        // Default status
        if (asset.getStatus() == null) {
            asset.setStatus("AVAILABLE");
        }

        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found with id " + id));
    }

    @Override
    public Asset updateStatus(Long id, String status) {
        Asset asset = getAsset(id);
        asset.setStatus(status);
        return assetRepository.save(asset);
    }
}
