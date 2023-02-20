package com.ironhack.demosecurityjwt.models.BankUser;

import com.ironhack.demosecurityjwt.Enuns.BankUserType;
import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ThirdParty extends User {
    private BankUserType bankUserType=BankUserType.THIRD_PARTY;


    private String hashedKey=String.valueOf(Objects.hash(LocalDateTime.now()));


    public ThirdParty(String name, String username, String password) {
        super(name, username, password);
    }

    public ThirdParty(String name) {
        super(name);
    }
}
