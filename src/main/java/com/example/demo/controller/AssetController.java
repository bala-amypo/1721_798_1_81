package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    /**
     * Create a new asset
     */
    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset created = assetService.createAsset(asset);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Get all assets
     */
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    /**
     * Get asset by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAsset(id));
    }

    /**
     * Update asset status
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Asset> updateAssetStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        Asset updated = assetService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
