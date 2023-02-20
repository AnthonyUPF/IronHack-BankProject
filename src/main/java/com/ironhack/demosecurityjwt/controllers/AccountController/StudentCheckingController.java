package com.ironhack.demosecurityjwt.controllers.AccountController;


import com.ironhack.demosecurityjwt.models.Account.StudentChecking;
import com.ironhack.demosecurityjwt.services.AccountService.Interfaces.StudentCheckingServiceInterface;
import com.ironhack.demosecurityjwt.services.AccountService.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class StudentCheckingController implements StudentCheckingServiceInterface {
    @Autowired
    StudentCheckingService studentCheckingService;

    @Override
    @GetMapping("/studentCheckings")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> getAllStudentCheckings() {
        return studentCheckingService.getAllStudentCheckings();
    }
}
