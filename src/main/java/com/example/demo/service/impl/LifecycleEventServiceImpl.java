package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.LifecycleEventService;
import java.util.List;

public class LifecycleEventServiceImpl implements LifecycleEventService {

    private final LifecycleEventRepository eventRepo;
    private final AssetRepository assetRepo;
    private final UserRepository userRepo;

    public LifecycleEventServiceImpl(LifecycleEventRepository eventRepo,
                                     AssetRepository assetRepo,
                                     UserRepository userRepo) {
        this.eventRepo = eventRepo;
        this.assetRepo = assetRepo;
        this.userRepo = userRepo;
    }

    public LifecycleEvent logEvent(Long assetId, Long userId, LifecycleEvent event) {
        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (event.getEventType() == null || event.getEventDescription().isEmpty()) {
            throw new ValidationException("Invalid event");
        }

        event.setAsset(asset);
        event.setPerformedBy(user);
        return eventRepo.save(event);
    }

    public List<LifecycleEvent> getEventsForAsset(Long assetId) {
        return eventRepo.findByAssetId(assetId);
    }

    public LifecycleEvent getEvent(Long id) {
        return eventRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }
}
