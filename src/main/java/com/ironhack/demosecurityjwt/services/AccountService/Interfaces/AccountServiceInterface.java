package com.ironhack.demosecurityjwt.services.AccountService.Interfaces;

import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public interface AccountServiceRepository {

    List<Account> getAllAccounts();
}
