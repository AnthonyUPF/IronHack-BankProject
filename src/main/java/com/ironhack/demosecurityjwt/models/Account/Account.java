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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Embedded
    private Money balance = new Money(new BigDecimal("0"));

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "primary_owner_user_id")
    private AccountHolder primaryOwner;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "secondary_owner_user_id")
    private AccountHolder secondaryOwner;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="currency_penalty")),
            @AttributeOverride(name="amount",column=@Column(name="amount_penalty"))
    })
    private Money penaltyFee = new Money(new BigDecimal("40"));

    private LocalDate creationDate = LocalDate.now();

    private String secretKey = String.valueOf(Objects.hash(LocalDateTime.now()));

    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    @OneToMany(mappedBy = "sendingAccount", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Transaction> sendingTransactionList = new ArrayList<>();

    @OneToMany(mappedBy = "receivingAccount", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Transaction> receivingTransactionList = new ArrayList<>();

    public Account(Money balance) {
        this.balance = balance;
    }

    public Account(Money balance, AccountHolder primaryOwner) {
        this.balance = balance;
        this.primaryOwner=primaryOwner;
    }





}
