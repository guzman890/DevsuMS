package com.backend.account.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    private Long id;

    @Column("date")
    private ZonedDateTime date;

    @Column("transaction_type")
    private String transactionType;

    @Column("amount")
    private Double amount;

    @Column("balance")
    private Double balance;

    @Column("account_id")
    private Long accountId;
}
