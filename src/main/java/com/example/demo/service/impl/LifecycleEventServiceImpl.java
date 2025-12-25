package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.LifecycleEvent;
import com.example.demo.repository.LifecycleEventRepository;
import com.example.demo.service.LifecycleEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LifecycleEventServiceImpl implements LifecycleEventService {

    private final LifecycleEventRepository repo;

    public LifecycleEventServiceImpl(LifecycleEventRepository repo) {
        this.repo = repo;
    }

    @Override
    public void recordEvent(Asset asset, String type, String description) {
        LifecycleEvent e = new LifecycleEvent();
        e.setAsset(asset);
        e.setEventType(type);
        e.setEventDescription(description);
        repo.save(e);
    }

    @Override
    public List<LifecycleEvent> getEventsByAsset(Long assetId) {
        return repo.findByAssetId(assetId);
    }
}
