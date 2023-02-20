package com.ironhack.demosecurityjwt.services.BankUserService.Interfaces;

import com.ironhack.demosecurityjwt.dtos.AccountDTO.AddressDTO;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.TransactionDTO;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccountHolderServiceInterface {

    List<AccountHolder> getAllAccountHolders();

    List<Account> getAllPrimaryOwnAccounts(Authentication authentication);

    List<Account> getAllSecondaryOwnAccounts(Authentication authentication);


    AccountHolder updateMailingAddress(AddressDTO addressDTO, Authentication authentication);

    AccountHolder updatePrimaryAddress(AddressDTO addressDTO, Authentication authentication);

    Transaction sendTransaction(TransactionDTO transactionDTO, Authentication authentication);




}
