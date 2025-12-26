package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
public class DisposalRecordController {

    private final DisposalRecordService service;

    public DisposalRecordController(DisposalRecordService service) {
        this.service = service;
    }

    @PostMapping("/{assetId}")
    public DisposalRecord create(@PathVariable Long assetId,
                                 @RequestBody DisposalRecord record) {
        return service.createDisposal(assetId, record);
    }

    @GetMapping
    public List<DisposalRecord> getAll() {
        return service.getAllDisposals();
    }

    @GetMapping("/{id}")
    public DisposalRecord get(@PathVariable Long id) {
        return service.getDisposal(id);
    }
}
