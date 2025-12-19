package com.example.demo.service;

import com.example.demo.entity.Asset;

import java.util.List;

public interface AssetService {

    Asset create(Asset asset);

    List<Asset> getAll();

    Asset getById(Long id);

    Asset updateStatus(Long id, String status);
}
