package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.LifecycleEventService;
import org.springframework.stereotype.Service;

@Service
public class DisposalServiceImpl {

    private final AssetRepository repo;
    private final LifecycleEventService eventService;

    public DisposalServiceImpl(AssetRepository repo, LifecycleEventService eventService) {
        this.repo = repo;
        this.eventService = eventService;
    }

    public Asset dispose(Long assetId, String reason) {
        Asset asset = repo.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        asset.setStatus("DISPOSED");
        repo.save(asset);

        eventService.recordEvent(asset, "DISPOSED", reason);
        return asset;
    }
}
