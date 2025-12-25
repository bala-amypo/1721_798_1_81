package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.LifecycleEventService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LifecycleEventServiceImpl implements LifecycleEventService {

    private final LifecycleEventRepository lifecycleEventRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public LifecycleEventServiceImpl(
            LifecycleEventRepository lifecycleEventRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {
        this.lifecycleEventRepository = lifecycleEventRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public LifecycleEvent logEvent(Long assetId, Long userId, LifecycleEvent event) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        event.setAsset(asset);
        event.setPerformedBy(user);
        return lifecycleEventRepository.save(event);
    }

    public List<LifecycleEvent> getEventsForAsset(Long assetId) {
        return lifecycleEventRepository.findByAsset_Id(assetId);
    }

    public LifecycleEvent getEvent(Long id) {
        return lifecycleEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }
}
