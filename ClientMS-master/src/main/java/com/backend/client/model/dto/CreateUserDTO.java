package com.backend.client.model.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String name;
    private String address;
    private String phone;
    private String password;
    private String status;
}
