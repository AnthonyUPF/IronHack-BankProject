package com.ironhack.demosecurityjwt.dtos.AccountDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class CreditCardDTO {


    private BigDecimal balance;

    private Integer accountHolderId;


    private BigDecimal creditLimit;


    private BigDecimal interestRate;

    public CreditCardDTO(BigDecimal balance, Integer accountHolderId, BigDecimal creditLimit, BigDecimal interestRate) {
        this.balance = balance;
        this.accountHolderId = accountHolderId;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }
}
