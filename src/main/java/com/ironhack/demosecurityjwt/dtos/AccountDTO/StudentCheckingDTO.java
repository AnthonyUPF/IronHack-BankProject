package com.ironhack.demosecurityjwt.dtos.AccountDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class StudentCheckingDTO {
    private BigDecimal balance;
    private Integer accountHolderId;

    private BigDecimal minimumBalance;

    public StudentCheckingDTO(BigDecimal balance, Integer accountHolderId, BigDecimal minimumBalance) {
        this.balance = balance;
        this.accountHolderId = accountHolderId;
        this.minimumBalance = minimumBalance;
    }
}
