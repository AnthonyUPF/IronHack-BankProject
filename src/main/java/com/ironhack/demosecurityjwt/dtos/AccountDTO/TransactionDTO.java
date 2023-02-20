package com.ironhack.demosecurityjwt.dtos.AccountDTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class TransactionDTO {

    @NotEmpty
    private Integer sendingAccountId;

    @NotEmpty
    private Integer  receivingAccountId;

    @PositiveOrZero
    private BigDecimal amount;

    public TransactionDTO(Integer sendingAccountId, Integer receivingAccountId, BigDecimal amount) {
        this.sendingAccountId = sendingAccountId;
        this.receivingAccountId = receivingAccountId;
        this.amount = amount;
    }
}
