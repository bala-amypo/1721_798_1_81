package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferRecordController {

    private final TransferRecordService service;

    public TransferRecordController(TransferRecordService service) {
        this.service = service;
    }

    @PostMapping("/{assetId}")
    public TransferRecord create(@PathVariable Long assetId,
                                 @RequestBody TransferRecord record) {
        return service.createTransfer(assetId, record);
    }

    @GetMapping("/asset/{assetId}")
    public List<TransferRecord> byAsset(@PathVariable Long assetId) {
        return service.getTransfersForAsset(assetId);
    }

    @GetMapping("/{id}")
    public TransferRecord get(@PathVariable Long id) {
        return service.getTransfer(id);
    }
}
