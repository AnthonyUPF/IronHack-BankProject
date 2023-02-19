package com.ironhack.demosecurityjwt.models.UserBank;


import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.repositories.AddressRepository.AddressRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class AccountHolder extends UserBank{



    private LocalDate dateOfBirth;


    @OneToOne
    @JoinColumn(name = "primary_address_address_id")
    private Address primaryAddress;
    private String mailingAddress;


    public AccountHolder(String name, LocalDate dateOfBirth) {
        super(name);
        this.dateOfBirth = dateOfBirth;
    }

    public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress =primaryAddress;
    }


}
