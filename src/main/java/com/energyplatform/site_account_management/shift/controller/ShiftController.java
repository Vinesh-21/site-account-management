package com.energyplatform.site_account_management.shift.controller;

import com.energyplatform.site_account_management.shift.dto.SingleShift;
import com.energyplatform.site_account_management.shift.entity.ShiftDetails;
import com.energyplatform.site_account_management.shift.service.ShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<ShiftDetails>> createShift(@RequestBody ShiftDetails shiftDetails) {

        if (shiftDetails.getShifts() == null) {
            shiftDetails.setShifts(new ArrayList<>());
        }

        return shiftService.createShift(shiftDetails)
                .map(shift ->
                        ResponseEntity.status(HttpStatus.CREATED)
                                .body(shift)
                );
    }

    @PutMapping("/{siteId}")
    public Mono<ShiftDetails> updateShift(
            @PathVariable String siteId,
            @RequestBody List<SingleShift> updateShift) {

        return shiftService.updateShift(siteId, updateShift);
    }

    @GetMapping
    public Flux<ShiftDetails> getShifts() {
        return shiftService.getShifts();
    }

    @GetMapping("/{siteId}")
    public Mono<ShiftDetails> getShift(@PathVariable String siteId) {
        return shiftService.getShiftById(siteId);
    }
}
