package com.ironhack.demosecurityjwt.dtos.AccountDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TransactionThirdPartyDTO {
    String hashedKey;
    BigDecimal amount;

    Integer accountId;

    String secretKey;
}
