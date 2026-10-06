package com.energyplatform.site_account_management.site.controller;

import com.energyplatform.site_account_management.site.entity.SiteDetails;
import com.energyplatform.site_account_management.site.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/sites")
public class SiteController {

    private final SiteService siteService;


    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SITE_CREATE')")
    public Mono<ResponseEntity<SiteDetails>> createSite(@RequestBody SiteDetails siteDetails) {

        if (siteDetails.getShiftConfigured() == null) {
            siteDetails.setShiftConfigured(false);
        }

        return siteService.createSite(siteDetails)
                .map(site->
                        ResponseEntity.status(HttpStatus.CREATED)
                                .body(site)
                );
    }

    @PutMapping("/{siteId}")
    public Mono<SiteDetails> updateSite(
            @PathVariable String siteId,
            @RequestBody SiteDetails siteDetails) {

        return siteService.updateSite(siteId, siteDetails);
    }

    @GetMapping
    public Flux<SiteDetails> getSites() {
        return siteService.getSites();
    }

    @GetMapping("/{siteId}")
    public Mono<SiteDetails> getSite(@PathVariable String siteId) {
        return siteService.getSiteById(siteId);
    }


}
