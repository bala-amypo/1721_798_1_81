package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;

import java.util.List;

public class LifecycleEventController {

    private final LifecycleEventService service;

    public LifecycleEventController(LifecycleEventService service) {
        this.service = service;
    }

    public LifecycleEvent log(Long assetId, Long userId, LifecycleEvent e) {
        return service.logEvent(assetId, userId, e);
    }

    public List<LifecycleEvent> getForAsset(Long assetId) {
        return service.getEventsForAsset(assetId);
    }
}
