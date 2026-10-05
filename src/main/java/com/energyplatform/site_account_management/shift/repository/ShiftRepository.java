package com.energyplatform.site_account_management.shift.repository;

import com.energyplatform.site_account_management.shift.entity.ShiftDetails;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ShiftRepository extends ReactiveMongoRepository<ShiftDetails, String> {
    Mono<ShiftDetails> findBySiteId(String siteId);
}
