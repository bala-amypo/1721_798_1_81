package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;

import java.util.List;

public class LifecycleEventServiceImpl {

    private final LifecycleEventRepository repo;
    private final AssetRepository assetRepo;
    private final UserRepository userRepo;

    public LifecycleEventServiceImpl(LifecycleEventRepository repo,
                                     AssetRepository assetRepo,
                                     UserRepository userRepo) {
        this.repo = repo;
        this.assetRepo = assetRepo;
        this.userRepo = userRepo;
    }

    public LifecycleEvent logEvent(Long assetId, Long userId, LifecycleEvent e) {
        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        e.setAsset(asset);
        e.setPerformedBy(user);
        e.prePersist();
        return repo.save(e);
    }

    public List<LifecycleEvent> getEventsForAsset(Long assetId) {
        return repo.findByAsset_Id(assetId);
    }
}
