package com.ironhack.demosecurityjwt.services.BankUserService;


import com.ironhack.demosecurityjwt.dtos.AccountDTO.TransactionThirdPartyDTO;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.AccountRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.ThirdPartyRepository;
import com.ironhack.demosecurityjwt.repositories.TransactionRepository.TransactionRepository;
import com.ironhack.demosecurityjwt.services.BankUserService.Interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ThirdPartyService implements ThirdPartyServiceInterface {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public List<ThirdParty> getAllThirdParties() {
        return thirdPartyRepository.findAll();
    }

    @Override
    public Transaction sendingMoney(TransactionThirdPartyDTO transactionThirdPartyDTO) {
        if(thirdPartyRepository.findByHashedKey(transactionThirdPartyDTO.getHashedKey())!=null){
            if(accountRepository.findBySecretKey(transactionThirdPartyDTO.getSecretKey())!=null) {
                Account account = accountRepository.findById(transactionThirdPartyDTO.getAccountId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not found"));

                Money amount=new Money(transactionThirdPartyDTO.getAmount());

                account.getBalance().increaseAmount(amount);
                accountRepository.save(account);

                return transactionRepository.save(new Transaction(null,account,amount));

            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid secretKey");
            }
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }


    @Override
    public Transaction receivingMoney(TransactionThirdPartyDTO transactionThirdPartyDTO) {
        if(thirdPartyRepository.findByHashedKey(transactionThirdPartyDTO.getHashedKey())!=null){
            if(accountRepository.findBySecretKey(transactionThirdPartyDTO.getSecretKey())!=null) {
                Account account = accountRepository.findById(transactionThirdPartyDTO.getAccountId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not found"));

                Money amount=new Money(transactionThirdPartyDTO.getAmount());

                if (account.getBalance().getAmount().compareTo(amount.getAmount()) >= 0) {

                    account.getBalance().decreaseAmount(amount);

                    accountRepository.save(account);

                    return transactionRepository.save(new Transaction(account,null,amount));
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
                }


            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid secretKey");
            }
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }


}
