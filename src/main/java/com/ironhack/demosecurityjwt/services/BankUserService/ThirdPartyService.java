package com.ironhack.demosecurityjwt.services.BankUserService;


import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.ThirdPartyRepository;
import com.ironhack.demosecurityjwt.services.BankUserService.Interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThirdPartyService implements ThirdPartyServiceInterface {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    @Override
    public List<ThirdParty> getAllThirdParties() {
        return thirdPartyRepository.findAll();
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
