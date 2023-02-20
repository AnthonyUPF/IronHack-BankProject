package com.ironhack.demosecurityjwt.services.AccountService;


import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.AccountRepository;
import com.ironhack.demosecurityjwt.services.AccountService.Interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
