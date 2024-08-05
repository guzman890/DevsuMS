package com.backend.account.model.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private String status;
    private Long clientId;
}