package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import com.example.demo.service.LifecycleEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repo;
    private final LifecycleEventService eventService;

    public AssetServiceImpl(AssetRepository repo, LifecycleEventService eventService) {
        this.repo = repo;
        this.eventService = eventService;
    }

    @Override
    public Asset createAsset(Asset asset) {
        Asset saved = repo.save(asset);
        eventService.recordEvent(saved, "CREATED", "Asset created");
        return saved;
    }

    @Override
    public Asset getAsset(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
    }

    @Override
    public List<Asset> getAllAssets() {
        return repo.findAll();
    }

    @Override
    public void deleteAsset(Long id) {
        Asset asset = getAsset(id);
        eventService.recordEvent(asset, "DELETED", "Asset deleted");
        repo.delete(asset);
    }
}
