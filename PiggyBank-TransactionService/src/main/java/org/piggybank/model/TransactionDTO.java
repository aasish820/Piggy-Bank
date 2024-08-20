package org.piggybank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long transactionId;
    private String transactionType;
    private Double amount;
    private Long accountId;
    private String transactionReason;
}

