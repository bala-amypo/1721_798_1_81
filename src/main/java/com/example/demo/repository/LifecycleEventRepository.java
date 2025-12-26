package com.example.demo.repository;

import com.example.demo.entity.LifecycleEvent;
import java.util.*;

public interface LifecycleEventRepository {
    LifecycleEvent save(LifecycleEvent event);
    Optional<LifecycleEvent> findById(Long id);
    List<LifecycleEvent> findByAsset_Id(Long assetId);
}
