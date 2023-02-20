package com.ironhack.demosecurityjwt.repositories.AccountRepository;

import com.ironhack.demosecurityjwt.models.Account.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking,Integer> {
}
