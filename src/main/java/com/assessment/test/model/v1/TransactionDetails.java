package com.assessment.test.model.v1;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDetails {
    private Long transId;
    private LocalDateTime transactionDate;
    private Double transAmount;
}
