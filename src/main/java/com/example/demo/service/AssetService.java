package com.example.demo.service;

import com.example.demo.entity.Asset;

public interface AssetService {
    Asset updateAssetStatus(Long assetId, String status);
}
