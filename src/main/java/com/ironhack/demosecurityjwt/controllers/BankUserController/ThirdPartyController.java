package com.ironhack.demosecurityjwt.controllers.BankUserController;


import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.ThirdPartyRepository;
import com.ironhack.demosecurityjwt.services.BankUserService.Interfaces.ThirdPartyServiceInterface;
import com.ironhack.demosecurityjwt.services.BankUserService.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bankUsers")
public class ThirdPartyController implements ThirdPartyServiceInterface {
    @Autowired
    ThirdPartyService thirdPartyService;


    @Override
    @GetMapping("/thirdParties")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAllThirdParties() {
        return thirdPartyService.getAllThirdParties();
    }

    @Override
    public Account sendMoney(String hashedKey, Money amount, Integer accountId, String accountSecretKey) {
        return null;
    }

    @Override
    public Account receiveMoney(String hashedKey, Money amount, Integer accountId, String accountSecretKey) {
        return null;
    }
}
