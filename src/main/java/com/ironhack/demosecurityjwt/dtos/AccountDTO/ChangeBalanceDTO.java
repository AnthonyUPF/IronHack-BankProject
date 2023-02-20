package com.ironhack.demosecurityjwt.dtos.AccountDTO;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ChangeBalanceDTO {
    private Integer accountId;

    private double amount;

    public ChangeBalanceDTO(Integer accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}
