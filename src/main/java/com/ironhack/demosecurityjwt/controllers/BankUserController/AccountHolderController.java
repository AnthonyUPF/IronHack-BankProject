package com.ironhack.demosecurityjwt.controllers.BankUserController;


import com.ironhack.demosecurityjwt.dtos.AccountDTO.AddressDTO;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.TransactionDTO;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AccountHolderRepository;
import com.ironhack.demosecurityjwt.services.BankUserService.AccountHolderService;
import com.ironhack.demosecurityjwt.services.BankUserService.Interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.FactoryConfigurationError;
import java.util.List;

@RestController
@RequestMapping("/api/bankUsers")
public class AccountHolderController implements AccountHolderServiceInterface {
    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Override
    @GetMapping("/accountHolders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderService.getAllAccountHolders();
    }



    @Override
    @GetMapping("/accountHolders/primaryOwnAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllPrimaryOwnAccounts(Authentication authentication) {
        return accountHolderService.getAllPrimaryOwnAccounts(authentication);
    }

    @Override
    @GetMapping("/accountHolders/secondaryOwnAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllSecondaryOwnAccounts(Authentication authentication) {
        return accountHolderService.getAllSecondaryOwnAccounts(authentication);
    }

    @Override
    @PutMapping("/accountHolders/updateMailingAddress")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountHolder updateMailingAddress(@RequestBody AddressDTO addressDTO, Authentication authentication) {
        return accountHolderService.updateMailingAddress(addressDTO, authentication);
    }

    @Override
    @PutMapping("/accountHolders/updatePrimaryAddress")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountHolder updatePrimaryAddress(@RequestBody AddressDTO addressDTO, Authentication authentication) {
        return accountHolderService.updatePrimaryAddress(addressDTO,authentication);
    }

    @Override
    @PatchMapping("/accountHolders/transaction")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transaction sendTransaction(@RequestBody TransactionDTO transactionDTO, Authentication authentication) {
        return accountHolderService.sendTransaction(transactionDTO,authentication);
    }


}
