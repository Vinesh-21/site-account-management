package com.energyplatform.site_account_management.shift.service;

import com.energyplatform.site_account_management.exception.ShiftException;
import com.energyplatform.site_account_management.shift.dto.SingleShift;
import com.energyplatform.site_account_management.shift.entity.ShiftDetails;
import com.energyplatform.site_account_management.shift.repository.ShiftRepository;
import com.energyplatform.site_account_management.site.repository.SiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final SiteRepository siteRepository;

    public Mono<ShiftDetails> createShift(ShiftDetails shiftDetails) {

        return siteRepository.findById(shiftDetails.getSiteId())
                .switchIfEmpty(
                        Mono.error(
                                new ShiftException(
                                        "Site not found: " + shiftDetails.getSiteId()
                                )
                        )
                )
                .flatMap(siteDetails ->
                        shiftRepository
                                .findBySiteId(shiftDetails.getSiteId())
                                .flatMap(existingShift ->
                                        Mono.<ShiftDetails>error(
                                                new ShiftException(
                                                        "Shift already exists for site: "
                                                                + shiftDetails.getSiteId()
                                                )
                                        )
                                )
                                .switchIfEmpty(
                                        shiftRepository.save(shiftDetails)
                                )
                );
    }


    public Flux<ShiftDetails> getShifts() {
        return shiftRepository.findAll();
    }

    public Mono<ShiftDetails> getShiftById(String shiftId) {
        return shiftRepository.findBySiteId(shiftId)
                .switchIfEmpty(
                        Mono.error(
                                new ShiftException(
                                        "Shift not found: " + shiftId
                                )
                        )
                );
    }

    public Mono<ShiftDetails> updateShift(
            String siteId,
            List<SingleShift> updateShift) {

        return shiftRepository.findBySiteId(siteId)
                .switchIfEmpty(
                        Mono.error(
                                new ShiftException("Shift not found for site: " + siteId)
                        )
                )
                .flatMap(existingShift -> {

                    existingShift.setShifts(updateShift);

                    return shiftRepository.save(existingShift);
                });
    }
}
