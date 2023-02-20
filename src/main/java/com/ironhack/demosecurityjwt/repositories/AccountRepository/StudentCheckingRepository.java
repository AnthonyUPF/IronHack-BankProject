package com.ironhack.demosecurityjwt.repositories.AccountRepository;

import com.ironhack.demosecurityjwt.models.Account.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking,Integer> {
}
