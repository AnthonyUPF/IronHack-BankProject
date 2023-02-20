package com.ironhack.demosecurityjwt.models.BankUser;

import com.ironhack.demosecurityjwt.Enuns.BankUserType;
import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Admin extends User {

    private BankUserType bankUserType=BankUserType.ADMIN;



    public Admin(String name, String username, String password) {
        super(name, username, password);

    }

    public Admin(String name) {
        super(name);
    }
}
