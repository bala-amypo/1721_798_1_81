package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
@Tag(name = "Disposals")
public class DisposalRecordController {

    private final DisposalRecordService disposalRecordService;

    public DisposalRecordController(
            DisposalRecordService disposalRecordService) {
        this.disposalRecordService = disposalRecordService;
    }

    @PostMapping("/{assetId}")
    public DisposalRecord createDisposal(
            @PathVariable Long assetId,
            @RequestBody DisposalRecord disposal) {
        return disposalRecordService.createDisposal(assetId, disposal);
    }

    @GetMapping
    public List<DisposalRecord> getAllDisposals() {
        return disposalRecordService.getAllDisposals();
    }

    @GetMapping("/{id}")
    public DisposalRecord getDisposal(@PathVariable Long id) {
        return disposalRecordService.getDisposal(id);
    }
}
