package com.ironhack.demosecurityjwt.models.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.demosecurityjwt.Enuns.AccountStatus;
import com.ironhack.demosecurityjwt.Enuns.AccountType;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;

import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@NoArgsConstructor
public class Account {

    /**

     The primary key for the Account table
     */
     @Id
     /**
     The account id is generated automatically by the database
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    /**

     The type of the account, either CHECKING or SAVINGS
     */
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    /**

     The balance of the account
     */
    @Embedded
    private Money balance = new Money(new BigDecimal("0"));

    /**

     The primary owner of the account
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "primary_owner_user_id")
    private AccountHolder primaryOwner;

    /**

     The secondary owner of the account
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "secondary_owner_user_id")
    private AccountHolder secondaryOwner;

    /**

     The penalty fee for the account
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="currency_penalty")),
            @AttributeOverride(name="amount",column=@Column(name="amount_penalty"))
    })
    private Money penaltyFee = new Money(new BigDecimal("40"));

    /**

     The date the account was created
     */
    private LocalDate creationDate = LocalDate.now();

    /**

     The secret key for the account
     */
    private String secretKey = String.valueOf(Objects.hash(LocalDateTime.now()));

    /**

     The status of the account, either ACTIVE, FROZEN, or CLOSED
     */
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    /**

     A list of transactions in which this account is the sending account
     */
    @OneToMany(mappedBy = "sendingAccount", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Transaction> sendingTransactionList = new ArrayList<>();

    /**

     A list of transactions in which this account is the receiving account
     */
    @OneToMany(mappedBy = "receivingAccount", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Transaction> receivingTransactionList = new ArrayList<>();

    /**

     Constructor for creating an account with a balance
     @param balance the initial balance of the account
     */
    public Account(Money balance) {
        this.balance = balance;
    }

    /**

     Constructor for creating an account with a balance and a primary owner
     @param balance the initial balance of the account
     @param primaryOwner the primary owner of the account
     */
    public Account(Money balance, AccountHolder primaryOwner) {
        this.balance = balance;
        this.primaryOwner=primaryOwner;
    }

}
