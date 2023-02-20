package com.ironhack.demosecurityjwt.controllers.AccountController;

import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.services.AccountService.AccountService;
import com.ironhack.demosecurityjwt.services.AccountService.Interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController implements AccountServiceInterface {
    @Autowired
    AccountService accountService;

    @Override
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
