package com.ironhack.demosecurityjwt.dtos.AccountDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class SavingsDTO {

    private String name;
    private LocalDate dateOfBirth;

    private String street;
    private String city;
    private String state;

    @Column(length = 5)
    private String zip;

    public SavingsDTO(String name, LocalDate dateOfBirth, String street, String city, String state, String zip) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
