package com.backend.account.model.dto;

import lombok.Data;

@Data
public class TransactionReportDTO {
    private String transactionType;
    private Double amount;
    private Double balance;
}
