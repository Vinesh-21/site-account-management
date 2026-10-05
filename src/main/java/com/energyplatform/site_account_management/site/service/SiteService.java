package com.energyplatform.site_account_management.site.service;

import com.energyplatform.site_account_management.exception.SiteException;
import com.energyplatform.site_account_management.site.entity.SiteDetails;
import com.energyplatform.site_account_management.site.repository.SiteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Mono<SiteDetails> createSite(SiteDetails siteDetails) {

        return siteRepository
                .findBySiteName(siteDetails.getSiteName())
                .flatMap(existingSite ->
                        Mono.<SiteDetails>error(
                                new SiteException(
                                        "Site already exists: "
                                                + siteDetails.getSiteName()
                                )
                        )
                )
                .switchIfEmpty(
                        siteRepository.save(siteDetails)
                );
    }

    public Flux<SiteDetails> getSites() {
        return siteRepository.findAll();
    }

    public Mono<SiteDetails> getSiteById(String siteId) {
        return siteRepository.findById(siteId)
                .switchIfEmpty(
                        Mono.error(
                                new SiteException(
                                        "Site not found: " + siteId
                                )
                        )
                );
    }

    public Mono<SiteDetails> updateSite(
            String siteId,
            SiteDetails siteDetails) {

        return siteRepository.findById(siteId)
                .switchIfEmpty(
                        Mono.error(
                                new SiteException("Site not found: " + siteId)
                        )
                )
                .flatMap(existingSite -> {

                    existingSite.setSiteName(siteDetails.getSiteName());
                    existingSite.setLocation(siteDetails.getLocation());
                    existingSite.setSiteLogoImage(siteDetails.getSiteLogoImage());
                    existingSite.setTimezone(siteDetails.getTimezone());
                    existingSite.setShiftConfigured(
                            siteDetails.getShiftConfigured()
                    );

                    return siteRepository.save(existingSite);
                });
    }
}
