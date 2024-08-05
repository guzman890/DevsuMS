package com.backend.account.model.dto;

import lombok.Data;

@Data
public class CreateAccountDTO {
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private String status;
    private String name;
}
