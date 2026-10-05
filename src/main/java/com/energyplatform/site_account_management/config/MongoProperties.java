package com.energyplatform.site_account_management.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "mongodb")
public class MongoProperties {

    private String uri;

    private Map<String, String> databases;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getDatabases() {
        return databases;
    }

    public void setDatabases(Map<String, String> databases) {
        this.databases = databases;
    }
}