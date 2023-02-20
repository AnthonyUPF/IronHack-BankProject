package com.ironhack.demosecurityjwt.services.BankUserService;


import com.ironhack.demosecurityjwt.Enuns.AccountType;
import com.ironhack.demosecurityjwt.Enuns.ChangeBalance;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.*;
import com.ironhack.demosecurityjwt.dtos.BankUserDTO.AccountHolderDTO;
import com.ironhack.demosecurityjwt.dtos.BankUserDTO.AdminDTO;
import com.ironhack.demosecurityjwt.dtos.BankUserDTO.ThirdPartyDTO;
import com.ironhack.demosecurityjwt.dtos.MessageDTO.DeleteMessageDTO;
import com.ironhack.demosecurityjwt.models.Account.*;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import com.ironhack.demosecurityjwt.models.BankUser.Admin;
import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.User;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.*;
import com.ironhack.demosecurityjwt.repositories.AddressRepository.AddressRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AccountHolderRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AdminRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.ThirdPartyRepository;
import com.ironhack.demosecurityjwt.repositories.TransactionRepository.TransactionRepository;
import com.ironhack.demosecurityjwt.repositories.UserRepository;
import com.ironhack.demosecurityjwt.services.BankUserService.Interfaces.AdminServiceInterface;
import com.ironhack.demosecurityjwt.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;


    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Override
    public List<Savings> getAllSavings(Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            return savingsRepository.findAll();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }

    @Override
    public List<CreditCard> getAllCreditCards(Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            return creditCardRepository.findAll();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }


    @Override
    public List<Admin> getAllAdmins(Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            return adminRepository.findAll();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }

    @Override
    public Account changeBalanceOfAnyAccount(ChangeBalanceDTO changeBalanceDTO, ChangeBalance changeBalance,Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            Account account = accountRepository.findById(changeBalanceDTO.getAccountId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));

            Money amount = new Money(new BigDecimal(changeBalanceDTO.getAmount()));

            switch (changeBalance) {
                case INCREASE_BALANCE:
                    account.getBalance().increaseAmount(amount);
                    break;
                case DECREASE_BALANCE:
                    if (account.getBalance().getAmount().compareTo(amount.getAmount()) < 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insufficient balance in the account");
                    }
                    account.getBalance().decreaseAmount(amount);
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid option");
            }
            return accountRepository.save(account);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }


    @Override
    public Account createAccount(AccountDTO accountDTO, AccountType accountType) {
        /*
        Address address=addressRepository.save(
                new Address(
                        accountDTO.getStreet(),
                        accountDTO.getCity(),
                        accountDTO.getState(),
                        accountDTO.getZip()
                )
        );

        AccountHolder accountHolder= new AccountHolder(
                        accountDTO.getName(),
                        accountDTO.getUserName(),
                        accountDTO.getPassword(),
                        accountDTO.getDateOfBirth(),
                        address
                        );

        accountHolder.setBankUserType(BankUserType.ACCOUNT_HOLDER);
        accountHolderRepository.save(accountHolder);

        userService.addRoleToUser(accountHolder.getUsername(), "ROLE_ACCOUNT_HOLDER");


        Money balance = new Money( accountDTO.getBalance());

        Money minimumBalance= new Money(accountDTO.getMinimumBalance());

        BigDecimal interestRateSavings=accountDTO.getInterestRateSavings();

        BigDecimal interestRateCreditCard=accountDTO.getInterestRateCreditCard();


        Money creditLimit=new Money(accountDTO.getCreditLimit());




        Account account = null;

        switch (accountType){
            case SAVINGS:

                account=savingsRepository.save(new Savings(balance,accountHolder,minimumBalance,interestRateSavings));
                break;
            case CREDIT_CARD:
                account=creditCardRepository.save(new CreditCard(accountType,balance,accountHolder,creditLimit,interestRateCreditCard));
                break;
            case CHECKING:
                Period age= Period.between(accountHolder.getDateOfBirth(), LocalDate.now());
                if(age.getYears()>24) {
                    account=checkingRepository.save(new Checking(accountHolder));
                }else{
                    accountType= AccountType.STUDENT_CHECKING;
                    account=studentCheckingRepository.save(new StudentChecking(accountType,accountHolder));
                }
                break;
            case STUDENT_CHECKING:
                account=studentCheckingRepository.save(new StudentChecking(accountType,accountHolder));
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid account type");
        }

         */
        return null;
    }

    @Override
    public Savings createSavings(SavingsDTO savingsDTO,Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            Money balance = new Money(savingsDTO.getBalance());
            AccountHolder accountHolder=accountHolderRepository.findById(savingsDTO.getAccountHolderId()).get();
            Money minimumBalance= new Money(savingsDTO.getMinimumBalance());
            BigDecimal interestRate=savingsDTO.getInterestRate();


            return  savingsRepository.save(new Savings(balance,accountHolder,minimumBalance,interestRate));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }

    }

    @Override
    public CreditCard createCreditCard(CreditCardDTO creditCardDTO,Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            Money balance = new Money(creditCardDTO.getBalance());
            AccountHolder accountHolder=accountHolderRepository.findById(creditCardDTO.getAccountHolderId()).get();
            Money creditLimit=new Money(creditCardDTO.getCreditLimit());
            BigDecimal interestRate=creditCardDTO.getInterestRate();

            return creditCardRepository.save(new CreditCard(balance,accountHolder,creditLimit,interestRate));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }

    @Override
    public Account createChecking(CheckingDTO checkingDTO, Authentication  authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {

            AccountHolder accountHolder = accountHolderRepository.findById(checkingDTO.getAccountHolderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account holder not found"));


            Money balance = new Money(checkingDTO.getBalance());

            Money minimumBalance = new Money(checkingDTO.getMinimumBalance());

            Period age = Period.between(accountHolder.getDateOfBirth(), LocalDate.now());

            Account account = null;

            if (age.getYears() > 24) {
                account = checkingRepository.save(new Checking(balance, accountHolder, minimumBalance));
            } else {
                account = studentCheckingRepository.save(new StudentChecking(balance, accountHolder));
            }

            return account;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }

    @Override
    public StudentChecking createStudentChecking(StudentCheckingDTO studentCheckingDTO, Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {

            AccountHolder accountHolder = accountHolderRepository.findById(studentCheckingDTO.getAccountHolderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account holder not found"));

            Money balance = new Money(studentCheckingDTO.getBalance());

            return studentCheckingRepository.save(new StudentChecking(balance,accountHolder));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }


    @Override
    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO,Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            Address primaryAddress=addressRepository.save(
                    new Address(
                            accountHolderDTO.getStreet(),
                            accountHolderDTO.getCity(),
                            accountHolderDTO.getState(),
                            accountHolderDTO.getZipCode()
                    )
            );

            Address mailingAddress=addressRepository.save(
                    new Address(
                            accountHolderDTO.getStreet(),
                            accountHolderDTO.getCity(),
                            accountHolderDTO.getState(),
                            accountHolderDTO.getZipCode()
                    )
            );


            AccountHolder accountHolder=  new AccountHolder(
                    accountHolderDTO.getName(),
                    accountHolderDTO.getUserName(),
                    passwordEncoder.encode(accountHolderDTO.getPassword()),
                    accountHolderDTO.getDateOfBirth(),
                    primaryAddress
            );

            accountHolder.setMailingAddress(mailingAddress);

            accountHolderRepository.save(accountHolder);

            userService.addRoleToUser(accountHolder.getUsername(), "ROLE_ACCOUNT_HOLDER");

            return accountHolder;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }

    }

    @Override
    public Admin createAdmin(AdminDTO adminDTO, Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            Admin admin=adminRepository.save(
                    new Admin(
                            adminDTO.getName(),
                            adminDTO.getUserName(),
                            passwordEncoder.encode(adminDTO.getPassword())
                    )
            );


            userService.addRoleToUser(admin.getUsername(), "ROLE_ADMIN");

            return admin;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }

    }

    @Override
    public ThirdParty createThirdParty(ThirdPartyDTO thirdPartyDTO, Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            ThirdParty thirdParty=thirdPartyRepository.save(
                    new ThirdParty(
                            thirdPartyDTO.getName(),
                            thirdPartyDTO.getUserName(),
                            null
                    )
            );

            userService.addRoleToUser(thirdParty.getUsername(), "ROLE_THIRD_PARTY");

            return thirdParty;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }

    @Override
    public DeleteMessageDTO deleteAccountHolder(Integer accountHolderId, Authentication authentication) {
        String username = authentication.getName();
        if (adminRepository.findByUsername(username) != null) {
            AccountHolder accountHolder=accountHolderRepository.findById(accountHolderId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account holder not found"));

            userRepository.delete(accountHolder);
            return new DeleteMessageDTO(accountHolder.getBankUserType().toString(),accountHolderId.toString());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }


}
