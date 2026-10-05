package com.energyplatform.site_account_management.shift.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleShift {

    private String shiftName;
    private Long shiftStart;
    private Long shiftEnd;

}
