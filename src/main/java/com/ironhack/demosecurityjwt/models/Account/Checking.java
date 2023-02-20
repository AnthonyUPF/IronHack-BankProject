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

    /**
     * The default monthly maintenance fee for a checking account.
     */
    private static final Money DEFAULT_MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal("12"));

    /**
     * The default minimum balance for a checking account.
     */
    private static final Money DEFAULT_MINIMUM_BALANCE  = new Money(new BigDecimal("250"));

    /**
     * The account type for a checking account is CHECKING.
     */
    private AccountType accountType=AccountType.CHECKING;

    /**
     * The monthly maintenance fee for the checking account.
     */
    @Embedded
    private Money monthlyMaintenanceFee=DEFAULT_MONTHLY_MAINTENANCE_FEE;

    /**
     * The minimum balance required for the checking account.
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="currency_minimum_balance")),
            @AttributeOverride(name="amount",column=@Column(name="amount_minimum_balance"))
    })
    private Money minimumBalance= DEFAULT_MINIMUM_BALANCE;

    /**
     * Constructor for a checking account.
     * @param balance The initial balance of the account.
     * @param primaryOwner The primary account holder.
     * @param minimumBalance The minimum balance required for the account.
     */
    public Checking(Money balance, AccountHolder primaryOwner, Money minimumBalance) {
        super(balance, primaryOwner);
        this.minimumBalance = minimumBalance;
    }
}
