package com.backend.account.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    private Long id;

    @Column("account_number")
    private String accountNumber;

    @Column("account_type")
    private String accountType;

    @Column("initial_balance")
    private Double initialBalance;

    @Column("status")
    private String status;

    @Column("client_id")
    private Long clientId;
}