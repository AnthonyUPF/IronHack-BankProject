package com.ironhack.demosecurityjwt.models.Account;

import com.ironhack.demosecurityjwt.Enuns.AccountStatus;
import com.ironhack.demosecurityjwt.Enuns.AccountType;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class StudentChecking extends Account {

    private AccountType accountType=AccountType.STUDENT_CHECKING;

    public StudentChecking(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
    }
}
