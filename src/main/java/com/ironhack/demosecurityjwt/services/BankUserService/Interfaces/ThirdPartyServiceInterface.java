package com.ironhack.demosecurityjwt.services.BankUserService.Interfaces;

import com.ironhack.demosecurityjwt.dtos.AccountDTO.TransactionThirdPartyDTO;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ThirdPartyServiceInterface {
    List<ThirdParty> getAllThirdParties();

    Transaction sendingMoney(TransactionThirdPartyDTO transactionThirdPartyDTO);

    Transaction receivingMoney(TransactionThirdPartyDTO transactionThirdPartyDTO);

}
