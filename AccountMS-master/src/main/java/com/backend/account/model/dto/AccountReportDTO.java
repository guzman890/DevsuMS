package com.backend.account.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountReportDTO {
    private String accountNumber;
    private String type;
    private List<TransactionReportDTO> transactions;
}
