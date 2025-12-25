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

        // NEVER trust client data
        asset.setId(null);
        asset.setCreatedAt(null);
        asset.setCurrentHolder(null);

        if (asset.getAssetTag() == null || asset.getAssetTag().isBlank()) {
            throw new ValidationException("Asset tag is required");
        }

        if (assetRepository.existsByAssetTag(asset.getAssetTag())) {
            throw new ValidationException("Asset tag already exists");
        }

        if (asset.getStatus() == null) {
            asset.setStatus("AVAILABLE");
        }

        return assetRepository.save(asset);
    }

    /* 🔥 CRITICAL FIX */
    @Override
    @Transactional(readOnly = true)
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
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
