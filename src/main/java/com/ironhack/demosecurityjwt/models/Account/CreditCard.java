package com.ironhack.demosecurityjwt.models.Account;


import com.ironhack.demosecurityjwt.Enuns.AccountStatus;
import com.ironhack.demosecurityjwt.Enuns.AccountType;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CreditCard extends Account {


    /**

     The maximum credit limit for a CreditCard account
     */
    private static final Money MAX_CREDIT_LIMIT = new Money(new BigDecimal("100000"));

    /**

     The default credit limit for a CreditCard account
     */
    private static final Money DEFAULT_CREDIT_LIMIT = new Money(new BigDecimal("100"));

    /**

     The default interest rate for a CreditCard account
     */
    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal("0.2");

    /**

     The minimum interest rate for a CreditCard account
     */
    private static final BigDecimal MIN_INTEREST_RATE = new BigDecimal("0.1");

    /**

     The account type for a CreditCard account
     */
    private AccountType accountType = AccountType.CREDIT_CARD;

    /**

     The current credit limit for this account
     */
    @Embedded
    private Money creditLimit = DEFAULT_CREDIT_LIMIT;

    /**

     The current interest rate for this account
     */
    private BigDecimal interestRate = DEFAULT_INTEREST_RATE;

    /**

     The date that interest was last added to this account
     */
    private LocalDateTime lastInterestAddedDate = LocalDateTime.now();

    /**

     Constructs a new CreditCard account with the given balance and primary owner
     @param balance the starting balance for this account
     @param primaryOwner the primary owner of this account
     */
    public CreditCard(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
    }

    /**

     Constructs a new CreditCard account with the given balance, primary owner, credit limit, and interest rate
     @param balance the starting balance for this account
     @param primaryOwner the primary owner of this account
     @param creditLimit the credit limit for this account
     @param interestRate the interest rate for this account
     */
    public CreditCard(Money balance, AccountHolder primaryOwner, Money creditLimit, BigDecimal interestRate) {
        super.setBalance(balance);
        super.setPrimaryOwner(primaryOwner);
        this.setCreditLimit(creditLimit);
        this.setInterestRate(interestRate);
    }

    /**

     Sets the credit limit for this account
     @param creditLimit the new credit limit for this account
     @throws ResponseStatusException if the new credit limit is not between the default and maximum limits
     */
    public void setCreditLimit(Money creditLimit) {
        if (creditLimit.getAmount().compareTo(DEFAULT_CREDIT_LIMIT.getAmount()) < 0 || creditLimit.getAmount().compareTo(MAX_CREDIT_LIMIT.getAmount()) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit limit should be between 100 and 100000");
        }
        this.creditLimit = creditLimit;
    }

    /**

     Sets the interest rate for this account
     @param interestRate the new interest rate for this account
     @throws ResponseStatusException if the new interest rate is not between the minimum and default rates
     */
    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(MIN_INTEREST_RATE) < 0 || interestRate.compareTo(DEFAULT_INTEREST_RATE) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Interest rate must be between 0.1 and 0.2");
        }
        this.interestRate = interestRate;
    }

    /**

     A method that adds interest to the CreditCard class.
     */

    public void addInterest() {
        LocalDateTime currentDate = LocalDateTime.now();
        long monthsPassed = ChronoUnit.MONTHS.between(lastInterestAddedDate, currentDate);
        if (monthsPassed >= 1) {
            BigDecimal interestRatePerMonth = interestRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
            BigDecimal interest = this.getBalance().getAmount().multiply(interestRatePerMonth);
            Money interestMoney = new Money(interest);
            this.getBalance().increaseAmount(interestMoney);
            lastInterestAddedDate = currentDate;
        }
    }

}
