package com.ironhack.demosecurityjwt.services.AccountService.Interfaces;

import com.ironhack.demosecurityjwt.models.Account.StudentChecking;

import java.util.List;

public interface StudentCheckingServiceInterface {
    List<StudentChecking> getAllStudentCheckings();
}
