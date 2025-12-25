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
        if (assetRepository.existsByAssetTag(asset.getAssetTag())) {
            throw new ValidationException("Asset tag already exists: " + asset.getAssetTag());
        }
        return assetRepository.save(asset);
    }
    
    @Override
    public Asset getAsset(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id: " + id));
    }
    
    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
    
    @Override
    public Asset updateStatus(Long assetId, String status) {
        Asset asset = getAsset(assetId);
        asset.setStatus(status);
        return assetRepository.save(asset);
    }
}