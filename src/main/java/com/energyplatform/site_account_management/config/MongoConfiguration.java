package com.energyplatform.site_account_management.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
@EnableConfigurationProperties(MongoProperties.class)
@EnableReactiveMongoAuditing
public class MongoConfiguration {

    private final MongoProperties properties;

    public MongoConfiguration(MongoProperties properties) {
        this.properties = properties;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(properties.getUri());
    }

    @Bean
    public ReactiveMongoTemplate metadataMongoTemplate(@Qualifier("mongoClient") MongoClient mongoClient) {
        return new ReactiveMongoTemplate(mongoClient, properties.getDatabases().get("metadata"));
    }

    @Bean
    public ReactiveMongoTemplate aggregationMongoTemplate(@Qualifier("mongoClient") MongoClient mongoClient) {
        return new ReactiveMongoTemplate(mongoClient, properties.getDatabases().get("aggregation"));
    }

    @Bean
    public ReactiveMongoTemplate realTimeMongoTemplate(@Qualifier("mongoClient") MongoClient mongoClient){
        return new ReactiveMongoTemplate(mongoClient,properties.getDatabases().get("realtime"));
    }
}
