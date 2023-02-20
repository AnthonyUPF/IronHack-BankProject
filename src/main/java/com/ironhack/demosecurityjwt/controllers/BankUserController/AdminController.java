package com.ironhack.demosecurityjwt.controllers.BankUserController;



import com.ironhack.demosecurityjwt.Enuns.AccountType;
import com.ironhack.demosecurityjwt.Enuns.ChangeBalance;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.*;
import com.ironhack.demosecurityjwt.dtos.BankUserDTO.AccountHolderDTO;
import com.ironhack.demosecurityjwt.dtos.BankUserDTO.AdminDTO;
import com.ironhack.demosecurityjwt.dtos.BankUserDTO.ThirdPartyDTO;
import com.ironhack.demosecurityjwt.dtos.MessageDTO.DeleteMessageDTO;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.Account.CreditCard;
import com.ironhack.demosecurityjwt.models.Account.Savings;
import com.ironhack.demosecurityjwt.models.Account.StudentChecking;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import com.ironhack.demosecurityjwt.models.BankUser.Admin;
import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.SavingsRepository;
import com.ironhack.demosecurityjwt.repositories.AddressRepository.AddressRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AccountHolderRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AdminRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.ThirdPartyRepository;
import com.ironhack.demosecurityjwt.services.BankUserService.AdminService;
import com.ironhack.demosecurityjwt.services.BankUserService.Interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankUsers")
public class AdminController implements AdminServiceInterface {
    @Autowired
    AdminService adminService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;


    @Override
    @GetMapping("/admins/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> getAllSavings(Authentication authentication) {
        return adminService.getAllSavings(authentication);
    }

    @Override
    @GetMapping("/admins/creditCards")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> getAllCreditCards(Authentication authentication) {
        return adminService.getAllCreditCards(authentication);
    }


    @Override
    @GetMapping("/admins/getAdmins")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> getAllAdmins(Authentication authentication) {
        return adminService.getAllAdmins(authentication);
    }

    @Override
    @PostMapping("/admins/changeBalanceOfAccounts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account changeBalanceOfAnyAccount(@RequestBody ChangeBalanceDTO changeBalanceDTO,@RequestParam ChangeBalance changeBalance,Authentication authentication) {
        return adminService.changeBalanceOfAnyAccount(changeBalanceDTO, changeBalance,authentication);
    }


    @Override
    @PostMapping("/admins/newAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody AccountDTO accountDTO, @RequestParam AccountType accountType) {
        return adminService.createAccount(accountDTO,accountType);
    }

    @Override
    @PostMapping("/admins/newSavings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavings(@RequestBody SavingsDTO savingsDTO,Authentication authentication) {
        return adminService.createSavings(savingsDTO,authentication);
    }

    @Override
    @PostMapping("/admins/newCreditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCard(@RequestBody CreditCardDTO creditCardDTO,Authentication authentication) {
        return adminService.createCreditCard(creditCardDTO,authentication);
    }

    @Override
    @PostMapping("/admins/newChecking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createChecking(CheckingDTO checkingDTO, Authentication authentication) {
        return adminService.createChecking(checkingDTO,authentication);
    }

    @Override
    @PostMapping("/admins/newStudentChecking")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking createStudentChecking(StudentCheckingDTO studentCheckingDTO, Authentication authentication) {
        return adminService.createStudentChecking(studentCheckingDTO,authentication);
    }


    @Override
    @PostMapping("/admins/newAccountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody AccountHolderDTO accountHolderDTO, Authentication authentication) {
        return adminService.createAccountHolder(accountHolderDTO, authentication);
    }

    @Override
    @PostMapping("/admins/newAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdmin(@RequestBody AdminDTO adminDTO,Authentication authentication) {
        return adminService.createAdmin(adminDTO, authentication);
    }

    @Override
    @PostMapping("/admins/newThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(ThirdPartyDTO thirdPartyDTO, Authentication authentication) {
        return adminService.createThirdParty(thirdPartyDTO,authentication);
    }

    @Override
    @PostMapping("/admins/deleteAccountHolder")
    public DeleteMessageDTO deleteAccountHolder(@RequestParam Integer accountHolderId, Authentication authentication) {
        return adminService.deleteAccountHolder(accountHolderId,authentication);
    }


}
