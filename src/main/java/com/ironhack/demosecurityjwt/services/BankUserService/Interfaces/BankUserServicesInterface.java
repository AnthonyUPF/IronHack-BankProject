package com.ironhack.demosecurityjwt.services.BankUserService.Interfaces;

import com.ironhack.demosecurityjwt.models.Account.Account;

import java.util.List;

public interface UserBankServicesInterface {
    List<Account> getAllBankUsers();
}
