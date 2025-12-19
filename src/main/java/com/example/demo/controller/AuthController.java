package com.example.demo.controller;

import com.example.demo.dto.AssetStatusUpdateRequest;
import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PutMapping("/{assetId}/status")
    public ResponseEntity<Asset> updateAssetStatus(
            @PathVariable Long assetId,
            @RequestBody AssetStatusUpdateRequest request) {

        Asset updatedAsset =
                assetService.updateAssetStatus(assetId, request.getStatus());
        return ResponseEntity.ok(updatedAsset);
    }
}
