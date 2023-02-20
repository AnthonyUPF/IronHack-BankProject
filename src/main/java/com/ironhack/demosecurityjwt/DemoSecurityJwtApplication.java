package com.ironhack.demosecurityjwt;


import com.ironhack.demosecurityjwt.Enuns.AccountStatus;
import com.ironhack.demosecurityjwt.models.Account.Checking;
import com.ironhack.demosecurityjwt.models.Account.CreditCard;
import com.ironhack.demosecurityjwt.models.Account.Savings;
import com.ironhack.demosecurityjwt.models.Account.StudentChecking;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.User;

import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import com.ironhack.demosecurityjwt.models.BankUser.Admin;
import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.CheckingRepository;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.CreditCardRepository;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.SavingsRepository;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.StudentCheckingRepository;
import com.ironhack.demosecurityjwt.repositories.AddressRepository.AddressRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AccountHolderRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AdminRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.ThirdPartyRepository;
import com.ironhack.demosecurityjwt.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class DemoSecurityJwtApplication implements CommandLineRunner{

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CreditCardRepository creditCardRepository;




    public static void main(String[] args) {
        SpringApplication.run(DemoSecurityJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role( "ROLE_USER"));
            userService.saveRole(new Role( "ROLE_ADMIN"));

            userService.saveRole(new Role( "ROLE_ACCOUNT_HOLDER"));
            userService.saveRole(new Role( "ROLE_THIRD_PARTY"));



            userService.saveUser(new User("John Doe", "john", "1234"));
            userService.saveUser(new User("James Smith", "james", "1234"));
            userService.saveUser(new User("Jane Carry", "jane", "1234"));
            userService.saveUser(new User("Chris Anderson", "chris", "1234"));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("james", "ROLE_ADMIN");

            userService.addRoleToUser("jane", "ROLE_ACCOUNT_HOLDER");

            userService.addRoleToUser("chris", "ROLE_ADMIN");
            userService.addRoleToUser("chris", "ROLE_USER");


        };
    }


    @Override
    public void run(String... args) throws Exception {
        /*

        adminRepository.save(
                new Admin("Admin 1")
        );

        accountHolderRepository.save(
                new AccountHolder("AccountHolder 1","Holder 1","1234" ,
                        LocalDate.of(1970,10,5),
                        addressRepository.save(new Address("street 1","city 1","state 1","00001")))
        );

        accountHolderRepository.save(
                new AccountHolder("AccountHolder 2","Holder 2","1234" ,
                        LocalDate.of(1980,10,5),
                        addressRepository.save(new Address("street 2","city 2","state 2","00002")))
        );

        thirdPartyRepository.save(
                new ThirdParty("ThirdParty 1")
        );

         */

    }



}
