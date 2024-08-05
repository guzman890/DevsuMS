package com.backend.account.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class HeaderReportDTO {
    private Date date;
    private String client;
    private List<AccountReportDTO> accounts;
}
