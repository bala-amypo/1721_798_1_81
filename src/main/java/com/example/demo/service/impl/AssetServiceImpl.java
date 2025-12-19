package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.exception.ResourceNotFoundException;
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
    public Asset create(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAll() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }

    @Override
    public Asset updateStatus(Long id, String status) {
        Asset asset = getById(id);
        asset.setStatus(status);
        return assetRepository.save(asset);
    }
}
