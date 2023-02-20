package com.ironhack.demosecurityjwt.services.BankUserService.Interfaces;


import com.ironhack.demosecurityjwt.Enuns.AccountType;
import com.ironhack.demosecurityjwt.Enuns.ChangeBalance;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.*;
import com.ironhack.demosecurityjwt.dtos.BankUserDTO.AccountHolderDTO;
import com.ironhack.demosecurityjwt.dtos.BankUserDTO.AdminDTO;
import com.ironhack.demosecurityjwt.dtos.MessageDTO.DeleteMessageDTO;
import com.ironhack.demosecurityjwt.models.Account.*;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import com.ironhack.demosecurityjwt.models.BankUser.Admin;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.models.User;
import org.springframework.security.core.Authentication;


import java.util.List;

public interface AdminServiceInterface {



    List<Savings> getAllSavings(Authentication authentication);

    List<CreditCard> getAllCreditCards(Authentication authentication);


    List<Admin> getAllAdmins(Authentication authentication);

    Account changeBalanceOfAnyAccount(ChangeBalanceDTO changeBalanceDTO, ChangeBalance changeBalance,Authentication authentication);

    Account createAccount(AccountDTO accountDTO, AccountType accountType);

    Savings createSavings(SavingsDTO savingsDTO, Authentication authentication);

    CreditCard createCreditCard(CreditCardDTO creditCardDTO, Authentication authentication);

    Account createChecking(CheckingDTO checkingDTO, Authentication authentication);

    StudentChecking createStudentChecking(StudentCheckingDTO studentCheckingDTO, Authentication authentication);

    AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO,Authentication authentication);

    Admin createAdmin(AdminDTO adminDTO);

    DeleteMessageDTO deleteAccountHolder(Integer accountHolderId);













}
