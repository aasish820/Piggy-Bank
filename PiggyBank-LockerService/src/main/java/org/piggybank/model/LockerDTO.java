package org.piggybank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockerDTO {
    private String lockerType;
    private String lockerNumber;
    private Boolean isAvailable;
    private Long account_id;
    private int monthlyFee;
}
