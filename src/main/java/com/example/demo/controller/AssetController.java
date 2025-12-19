package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    // POST /api/assets
    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return assetService.create(asset);
    }

    // GET /api/assets
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAll();
    }

    // GET /api/assets/{id}
    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable Long id) {
        return assetService.getById(id);
    }

    // PUT /api/assets/status/{id}
    @PutMapping("/status/{id}")
    public Asset updateAssetStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return assetService.updateStatus(id, status);
    }
}
