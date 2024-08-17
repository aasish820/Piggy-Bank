package org.piggybank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockerDTO {

    private Long lockerId;
    private String lockerType;
    private String lockerNumber;
    private Boolean isAvailable;
    private Long accountId;
    private int monthlyFee;
}
