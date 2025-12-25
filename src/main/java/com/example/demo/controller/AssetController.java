package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Assets", description = "Asset management endpoints")
public class AssetController {
    
    private final AssetService assetService;
    
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new asset")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset created = assetService.createAsset(asset);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    @Operation(summary = "Get all assets")
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get asset by ID")
    public ResponseEntity<Asset> getAsset(@PathVariable Long id) {
        Asset asset = assetService.getAsset(id);
        return ResponseEntity.ok(asset);
    }
    
    @PutMapping("/status/{id}")
    @Operation(summary = "Update asset status")
    public ResponseEntity<Asset> updateStatus(@PathVariable Long id, @RequestParam String status) {
        Asset updated = assetService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}