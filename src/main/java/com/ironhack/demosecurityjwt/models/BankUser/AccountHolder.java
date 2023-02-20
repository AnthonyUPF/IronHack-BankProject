package com.ironhack.demosecurityjwt.models.BankUser;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.demosecurityjwt.Enuns.BankUserType;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class AccountHolder extends User {

    private BankUserType bankUserType=BankUserType.ACCOUNT_HOLDER;

    private LocalDate dateOfBirth;




    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "primary_address_address_id")
    private Address primaryAddress;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "mailing_address_address_id")
    private Address mailingAddress;

    @OneToMany(mappedBy = "primaryOwner", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Account> primaryAccountList = new ArrayList<>();

    @OneToMany(mappedBy = "secondaryOwner", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Account> secondaryAccountList = new ArrayList<>();



    public AccountHolder(String name, String username, String password, LocalDate dateOfBirth, Address primaryAddress) {
        super(name, username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }
}
