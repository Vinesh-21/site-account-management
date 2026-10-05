package com.energyplatform.site_account_management.site.repository;

import com.energyplatform.site_account_management.site.entity.SiteDetails;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SiteRepository extends ReactiveMongoRepository<SiteDetails, String> {
    Mono<SiteDetails> findBySiteName(String siteName);
}
