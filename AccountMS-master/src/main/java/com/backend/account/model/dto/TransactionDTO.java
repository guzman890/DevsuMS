package com.backend.account.model.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private ZonedDateTime date;
    private String transactionType;
    private Double amount;
    private Double balance;
    private Long accountId;
}