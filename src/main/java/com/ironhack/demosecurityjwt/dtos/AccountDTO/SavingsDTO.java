package com.ironhack.demosecurityjwt.dtos.AccountDTO;


import com.ironhack.demosecurityjwt.models.Money.Money;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SavingsDTO {


    private BigDecimal balance;
    private Integer accountHolderId;

    private BigDecimal minimumBalance;

    private BigDecimal interestRate;

    public SavingsDTO(BigDecimal balance, Integer accountHolderId, BigDecimal minimumBalance, BigDecimal interestRate) {
        this.balance = balance;
        this.accountHolderId = accountHolderId;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }
}


