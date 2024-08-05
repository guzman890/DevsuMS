package com.backend.client.model.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String password;
    private String status;
    private Long personId;
}
