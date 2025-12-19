package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
public class DisposalRecordController {

    private final DisposalRecordService disposalRecordService;

    public DisposalRecordController(DisposalRecordService disposalRecordService) {
        this.disposalRecordService = disposalRecordService;
    }

    // POST /api/disposals/{assetId}
    @PostMapping("/{assetId}")
    public DisposalRecord createDisposal(
            @PathVariable Long assetId,
            @RequestBody DisposalRecord record) {
        return disposalRecordService.create(assetId, record);
    }

    // GET /api/disposals
    @GetMapping
    public List<DisposalRecord> getAllDisposals() {
        return disposalRecordService.getAll();
    }

    // GET /api/disposals/{id}
    @GetMapping("/{id}")
    public DisposalRecord getDisposal(@PathVariable Long id) {
        return disposalRecordService.getById(id);
    }
}
