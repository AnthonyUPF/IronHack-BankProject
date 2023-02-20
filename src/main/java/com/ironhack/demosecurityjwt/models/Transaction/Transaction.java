package com.ironhack.demosecurityjwt.models.Transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private LocalDateTime transactionDateAndTime=LocalDateTime.now();


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sending_account_account_id")
    private Account sendingAccount;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "receiving_account_account_id")
    private Account  receivingAccount;

    @Embedded
    private Money amount;

    public Transaction(Account sendingAccount, Account receivingAccount, Money amount) {
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
        this.amount = amount;
    }
}
