package com.ironhack.demosecurityjwt.dtos.BankUserDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ThirdPartyDTO {
    @NotNull
    private String name;


    @NotNull
    private String userName;



}
