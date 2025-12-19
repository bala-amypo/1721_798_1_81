package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class LifecycleEventController {

    private final LifecycleEventService lifecycleEventService;

    public LifecycleEventController(LifecycleEventService lifecycleEventService) {
        this.lifecycleEventService = lifecycleEventService;
    }

    // POST /api/events/{assetId}/{userId}
    @PostMapping("/{assetId}/{userId}")
    public LifecycleEvent logEvent(
            @PathVariable Long assetId,
            @PathVariable Long userId,
            @RequestBody LifecycleEvent event) {
        return lifecycleEventService.logEvent(assetId, userId, event);
    }

    // GET /api/events/asset/{assetId}
    @GetMapping("/asset/{assetId}")
    public List<LifecycleEvent> getEventsForAsset(@PathVariable Long assetId) {
        return lifecycleEventService.getByAsset(assetId);
    }

    // GET /api/events/{id}
    @GetMapping("/{id}")
    public LifecycleEvent getEvent(@PathVariable Long id) {
        return lifecycleEventService.getById(id);
    }
}
