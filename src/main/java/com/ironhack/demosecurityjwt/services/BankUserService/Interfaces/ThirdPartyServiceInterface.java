package com.ironhack.demosecurityjwt.services.BankUserService.Interfaces;

import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;

import java.util.List;

public interface ThirdPartyServiceInterface {
    List<ThirdParty> getAllThirdParties();

    Account sendMoney(String hashedKey, Money amount,Integer accountId,String accountSecretKey);

    Account receiveMoney(String hashedKey, Money amount,Integer accountId,String accountSecretKey);

}
