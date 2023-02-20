package com.ironhack.demosecurityjwt.controllers.BankUserController;


import com.ironhack.demosecurityjwt.dtos.AccountDTO.TransactionThirdPartyDTO;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.ThirdPartyRepository;
import com.ironhack.demosecurityjwt.services.BankUserService.Interfaces.ThirdPartyServiceInterface;
import com.ironhack.demosecurityjwt.services.BankUserService.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankUsers")
public class ThirdPartyController implements ThirdPartyServiceInterface {
    @Autowired
    ThirdPartyService thirdPartyService;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;


    @Override
    @GetMapping("/thirdParties")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAllThirdParties() {
        return thirdPartyService.getAllThirdParties();
    }

    @Override
    @GetMapping("/thirdParties/sendingMoney")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transaction sendingMoney(@RequestBody TransactionThirdPartyDTO transactionThirdPartyDTO) {
        return thirdPartyService.sendingMoney(transactionThirdPartyDTO);
    }

    @Override
    @GetMapping("/thirdParties/receivingMoney")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transaction receivingMoney(TransactionThirdPartyDTO transactionThirdPartyDTO) {
        return thirdPartyService.receivingMoney(transactionThirdPartyDTO);
    }


}
