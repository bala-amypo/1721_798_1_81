package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;

import java.util.List;

public class AssetController {

    private final AssetService service;

    public AssetController(AssetService service) {
        this.service = service;
    }

    public Asset create(Asset asset) {
        return service.createAsset(asset);
    }

    public Asset get(Long id) {
        return service.getAsset(id);
    }

    public List<Asset> getAll() {
        return service.getAllAssets();
    }

    public Asset updateStatus(Long id, String status) {
        return service.updateStatus(id, status);
    }
}
