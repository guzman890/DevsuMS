package com.backend.account.model.dto;

import lombok.Data;

@Data
public class CreateTransactionDTO {
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private String status;
    private Double amount;
}
