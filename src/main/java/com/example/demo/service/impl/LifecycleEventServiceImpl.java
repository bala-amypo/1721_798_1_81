package com.example.demo.service;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.repository.LifecycleEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LifecycleEventServiceImpl {

    private final LifecycleEventRepository repository;

    public LifecycleEvent logEvent(Long assetId, Long userId, LifecycleEvent event) {
        return repository.save(event);
    }
}
