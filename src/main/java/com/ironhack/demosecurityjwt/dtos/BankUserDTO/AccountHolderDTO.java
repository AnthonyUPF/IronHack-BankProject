package com.ironhack.demosecurityjwt.dtos.BankUserDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class AccountHolderDTO {
    @NotNull
    private String name;


    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private LocalDate dateOfBirth;

    private String street;
    private String city;
    private String state;

    @Column(length = 5)
    private String zipCode;


    public AccountHolderDTO(String name, String userName, String password, LocalDate dateOfBirth, String street, String city, String state, String zip) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zip;
    }
}
