package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.LifecycleEvent;
import java.util.List;

public interface LifecycleEventService {
    void recordEvent(Asset asset, String type, String description);
    List<LifecycleEvent> getEventsByAsset(Long assetId);
}
