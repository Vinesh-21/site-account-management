package com.energyplatform.site_account_management.site.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "site_details")
public class SiteDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String siteName;

    private String location;

    /**
     * Identifier or URL for the logo image stored in MongoDB.
     */
    private String siteLogoImage;

    private String timezone;

    private Boolean shiftConfigured=false;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
