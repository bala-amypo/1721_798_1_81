package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;

public class DisposalRecordController {

    private final DisposalRecordService service;

    public DisposalRecordController(DisposalRecordService service) {
        this.service = service;
    }

    public DisposalRecord create(Long assetId, DisposalRecord dr) {
        return service.createDisposal(assetId, dr);
    }

    public DisposalRecord get(Long id) {
        return service.getDisposal(id);
    }
}
