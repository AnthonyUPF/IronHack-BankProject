package com.ironhack.demosecurityjwt.models.Account;


import com.ironhack.demosecurityjwt.Enuns.AccountStatus;
import com.ironhack.demosecurityjwt.Enuns.AccountType;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;


@Entity
@Getter
@NoArgsConstructor
public class Savings extends Account {

    private static final Money DEFAULT_MINIMUM_BALANCE = new Money(new BigDecimal("1000"));
    private static final Money MINIMUM_BALANCE_WITH_EXCEPTION = new Money(new BigDecimal("100"));

    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.0025");
    private static final BigDecimal MAX_INTEREST_RATE = new BigDecimal("0.5");

    private AccountType accountType=AccountType.SAVINGS;


    @Embedded
    private Money minimumBalance=DEFAULT_MINIMUM_BALANCE;

    private BigDecimal interestRate=DEFAULT_INTEREST_RATE;

    private LocalDateTime lastInterestAddedDate=LocalDateTime.now();


    public Savings(Money balance, AccountHolder primaryOwner) {
        super.setBalance(balance);
        super.setPrimaryOwner(primaryOwner);
    }

    public Savings(Money balance, AccountHolder primaryOwner, Money minimumBalance, BigDecimal interestRate) {
        super.setBalance(balance);
        super.setPrimaryOwner(primaryOwner);
        this.setMinimumBalance(minimumBalance);
        this.setInterestRate(interestRate);
    }

    @Override
    public void setBalance(Money balance) {
        super.setBalance(balance);
        if (balance.getAmount().compareTo(minimumBalance.getAmount()) < 0) {
            this.getBalance().decreaseAmount(super.getPenaltyFee());
        }
    }


    public void setMinimumBalance(Money minimumBalance) {
        if (minimumBalance.getAmount().compareTo(DEFAULT_MINIMUM_BALANCE.getAmount()) > 0 || minimumBalance.getAmount().compareTo(MINIMUM_BALANCE_WITH_EXCEPTION.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Minimum balance should be between 100 and 1000");
        }
        this.minimumBalance = minimumBalance;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(MAX_INTEREST_RATE) > 0 || interestRate.compareTo(DEFAULT_INTEREST_RATE) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interest rate should be between 0.0025 and 0.5");
        }
        this.interestRate = interestRate;
    }

    public void addInterest() {
        LocalDateTime currentDate = LocalDateTime.now();
        long yearsPassed = ChronoUnit.YEARS.between(lastInterestAddedDate, currentDate);
        if (yearsPassed >= 1) {
            BigDecimal interest = this.getBalance().getAmount().multiply(interestRate);
            Money interestMoney = new Money(interest);
            this.getBalance().increaseAmount(interestMoney);
            lastInterestAddedDate = currentDate;
        }
    }

}


