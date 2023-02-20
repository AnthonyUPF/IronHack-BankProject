package com.ironhack.demosecurityjwt.dtos.BankUserDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class AdminDTO {
    @NotNull
    private String name;


    @NotNull
    private String userName;

    @NotNull
    private String password;


}
