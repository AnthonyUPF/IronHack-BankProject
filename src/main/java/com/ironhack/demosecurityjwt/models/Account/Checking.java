package com.ironhack.demosecurityjwt.models.Account;

import com.ironhack.demosecurityjwt.Enuns.AccountStatus;
import com.ironhack.demosecurityjwt.Enuns.AccountType;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Checking extends Account{

    private static final Money DEFAULT_MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal("12"));

    private static final Money DEFAULT_MINIMUM_BALANCE  = new Money(new BigDecimal("250"));


    private AccountType accountType=AccountType.CHECKING;

    @Embedded
    private Money monthlyMaintenanceFee=DEFAULT_MONTHLY_MAINTENANCE_FEE;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="currency_minimum_balance")),
            @AttributeOverride(name="amount",column=@Column(name="amount_minimum_balance"))
    })
    private Money minimumBalance= DEFAULT_MINIMUM_BALANCE;


    public Checking(Money balance, AccountHolder primaryOwner, Money minimumBalance) {
        super(balance, primaryOwner);
        this.minimumBalance = minimumBalance;
    }
}
