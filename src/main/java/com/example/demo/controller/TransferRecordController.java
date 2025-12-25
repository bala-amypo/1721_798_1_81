package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Transfers", description = "Asset transfer management endpoints")
public class TransferRecordController {
    
    private final TransferRecordService transferRecordService;
    
    public TransferRecordController(TransferRecordService transferRecordService) {
        this.transferRecordService = transferRecordService;
    }
    
    @PostMapping("/{assetId}")
    @Operation(summary = "Create a transfer record")
    public ResponseEntity<TransferRecord> createTransfer(
            @PathVariable Long assetId,
            @RequestBody TransferRecord record) {
        TransferRecord created = transferRecordService.createTransfer(assetId, record);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/asset/{assetId}")
    @Operation(summary = "Get all transfers for an asset")
    public ResponseEntity<List<TransferRecord>> getTransfersForAsset(@PathVariable Long assetId) {
        List<TransferRecord> transfers = transferRecordService.getTransfersForAsset(assetId);
        return ResponseEntity.ok(transfers);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get transfer by ID")
    public ResponseEntity<TransferRecord> getTransfer(@PathVariable Long id) {
        TransferRecord transfer = transferRecordService.getTransfer(id);
        return ResponseEntity.ok(transfer);
    }
}