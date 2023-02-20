package com.ironhack.demosecurityjwt.controllers.AccountController;


import com.ironhack.demosecurityjwt.models.Account.Checking;
import com.ironhack.demosecurityjwt.services.AccountService.CheckingService;
import com.ironhack.demosecurityjwt.services.AccountService.Interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class CheckingController implements CheckingServiceInterface {
    @Autowired
    CheckingService checkingService;


    @Override
    @GetMapping("/checkings")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> getAllCheckings() {
        return checkingService.getAllCheckings();
    }
}
