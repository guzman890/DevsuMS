package com.backend.client.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Table(name = "persons")
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity{

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("gender")
    private String gender;

    @Column("age")
    private int age;

    @Column("identification")
    private String identification;

    @Column("address")
    private String address;

    @Column("phone")
    private String phone;
}
