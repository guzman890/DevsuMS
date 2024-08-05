package com.backend.client.model.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    private Long id;

    @Column("password")
    private String password;

    @Column("status")
    private String status;

    @Column("person_id")
    private Long personId;

}
