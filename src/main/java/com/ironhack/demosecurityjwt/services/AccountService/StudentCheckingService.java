package com.ironhack.demosecurityjwt.services.AccountService;


import com.ironhack.demosecurityjwt.models.Account.StudentChecking;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.StudentCheckingRepository;
import com.ironhack.demosecurityjwt.services.AccountService.Interfaces.StudentCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCheckingService implements StudentCheckingServiceInterface {
    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Override
    public List<StudentChecking> getAllStudentCheckings() {
        return studentCheckingRepository.findAll();
    }
}
