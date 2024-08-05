package com.backend.account.web.model;

import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String password;
    private String status;
    private Long personId;
}
