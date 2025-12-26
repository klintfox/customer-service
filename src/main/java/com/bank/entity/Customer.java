package com.bank.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.UUID;

@Entity
public class Customer extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @NotBlank
    public String name;

    @NotBlank
    @Column(unique = true)
    public String document;

    @Email
    @NotBlank
    public String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    public Status status;

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
