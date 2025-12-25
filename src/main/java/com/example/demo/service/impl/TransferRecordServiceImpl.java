package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.LifecycleEventService;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl {

    private final AssetRepository repo;
    private final LifecycleEventService eventService;

    public TransferServiceImpl(AssetRepository repo, LifecycleEventService eventService) {
        this.repo = repo;
        this.eventService = eventService;
    }

    public Asset transfer(Long assetId, String newOwner) {
        Asset asset = repo.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        asset.setOwner(newOwner);
        repo.save(asset);

        eventService.recordEvent(asset, "TRANSFERRED", "Transferred to " + newOwner);
        return asset;
    }
}
