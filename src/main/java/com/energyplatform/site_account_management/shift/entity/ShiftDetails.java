package com.energyplatform.site_account_management.shift.entity;

import com.energyplatform.site_account_management.shift.dto.SingleShift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("shifts")
public class ShiftDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String siteId;

    private List<SingleShift> shifts;


    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
