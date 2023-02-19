package com.ironhack.demosecurityjwt.models.UserBank;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Setter
@Getter
@NoArgsConstructor
public class Admin extends UserBank{

    public Admin(String name) {
        super(name);
    }
}
