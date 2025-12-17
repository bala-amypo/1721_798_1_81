package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class AssetServiceImpl implements AssetService {

    @Override
    public Asset createAsset(Asset asset) {
        return asset;
    }

    @Override
    public List<Asset> getAllAssets() {
        return new ArrayList<>();
    }

    @Override
    public Asset getAssetById(Long id) {
        return null;
    }
}
