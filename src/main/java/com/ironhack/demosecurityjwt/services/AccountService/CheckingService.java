package com.ironhack.demosecurityjwt.services.AccountService;

import com.ironhack.demosecurityjwt.models.Account.Checking;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.CheckingRepository;
import com.ironhack.demosecurityjwt.services.AccountService.Interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingService implements CheckingServiceInterface {
    @Autowired
    CheckingRepository checkingRepository;


    @Override
    public List<Checking> getAllCheckings() {
        return null;
    }
}
