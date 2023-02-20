package com.ironhack.demosecurityjwt.services.BankUserService;


import com.ironhack.demosecurityjwt.dtos.AccountDTO.AddressDTO;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.TransactionDTO;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.AccountRepository;
import com.ironhack.demosecurityjwt.repositories.AddressRepository.AddressRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AccountHolderRepository;
import com.ironhack.demosecurityjwt.repositories.TransactionRepository.TransactionRepository;
import com.ironhack.demosecurityjwt.services.BankUserService.Interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderRepository.findAll();
    }

    @Override
    public List<Account> getAllPrimaryOwnAccounts(Authentication authentication) {
        String username = authentication.getName();
        if (accountHolderRepository.findByUsername(username) != null) {
            return accountHolderRepository.findByUsername(username).getPrimaryAccountList();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }

    @Override
    public List<Account> getAllSecondaryOwnAccounts(Authentication authentication) {
        String username = authentication.getName();
        if (accountHolderRepository.findByUsername(username) != null) {
            return accountHolderRepository.findByUsername(username).getSecondaryAccountList();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }

    @Override
    public AccountHolder updateMailingAddress(AddressDTO addressDTO,Authentication authentication) {
        String username = authentication.getName();
        if (accountHolderRepository.findByUsername(username) != null) {
            AccountHolder accountHolder=accountHolderRepository.findById(addressDTO.getAccountHolderId()).get();

            accountHolder.getMailingAddress().setStreet(addressDTO.getStreet());
            accountHolder.getMailingAddress().setState(addressDTO.getState());
            accountHolder.getMailingAddress().setCity(addressDTO.getCity());
            accountHolder.getMailingAddress().setZipCode(addressDTO.getZipCode());

            return accountHolderRepository.save(accountHolder);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }


    @Override
    public AccountHolder updatePrimaryAddress(AddressDTO addressDTO, Authentication authentication) {
        String username = authentication.getName();
        if (accountHolderRepository.findByUsername(username) != null) {
            AccountHolder accountHolder=accountHolderRepository.findById(addressDTO.getAccountHolderId()).get();

            accountHolder.getPrimaryAddress().setStreet(addressDTO.getStreet());
            accountHolder.getPrimaryAddress().setState(addressDTO.getState());
            accountHolder.getPrimaryAddress().setCity(addressDTO.getCity());
            accountHolder.getPrimaryAddress().setZipCode(addressDTO.getZipCode());

            return accountHolderRepository.save(accountHolder);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }

    @Override
    public Transaction sendTransaction(TransactionDTO transactionDTO, Authentication authentication) {
        String username = authentication.getName();

        if (accountHolderRepository.findByUsername(username) != null) {
            Account sendingAccount = accountRepository.findById(transactionDTO.getSendingAccountId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sending account not found"));

            Account receivingAccount = accountRepository.findById(transactionDTO.getReceivingAccountId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receiving account not found"));

            Money amount=new Money(transactionDTO.getAmount());

            if (sendingAccount.getBalance().getAmount().compareTo(amount.getAmount()) >= 0) {

                sendingAccount.getBalance().decreaseAmount(amount);
                receivingAccount.getBalance().increaseAmount(amount);

                accountRepository.save(sendingAccount);
                accountRepository.save(receivingAccount);

                return transactionRepository.save(new Transaction(sendingAccount, receivingAccount, amount));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }


}
