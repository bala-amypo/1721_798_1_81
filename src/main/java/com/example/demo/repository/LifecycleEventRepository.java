package com.example.demo.repository;

import com.example.demo.entity.LifecycleEvent;

import java.util.List;

public interface LifecycleEventRepository {
    LifecycleEvent save(LifecycleEvent event);
    List<LifecycleEvent> findByAsset_Id(Long assetId);
}
