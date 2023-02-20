package com.ironhack.demosecurityjwt.dtos.AccountDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
public class AccountDTO {


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
    private String zip;


    private BigDecimal balance;



    private BigDecimal interestRateSavings;

    private BigDecimal interestRateCreditCard;

    private BigDecimal minimumBalance;


    private BigDecimal creditLimit;


    public AccountDTO(String name, String userName, String password, LocalDate dateOfBirth, String street, String city, String state, String zip, BigDecimal balance, BigDecimal interestRateSavings, BigDecimal interestRateCreditCard, BigDecimal minimumBalance, BigDecimal creditLimit) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.balance = balance;
        this.interestRateSavings = interestRateSavings;
        this.interestRateCreditCard = interestRateCreditCard;
        this.minimumBalance = minimumBalance;
        this.creditLimit = creditLimit;
    }
}
