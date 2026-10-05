package com.energyplatform.site_account_management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(
        basePackages = {"com.energyplatform.site_account_management.site.repository",
                "com.energyplatform.site_account_management.shift.repository"},
        reactiveMongoTemplateRef = "metadataMongoTemplate"
)
public class MetadataMongoRepositoryConfiguration {
}
