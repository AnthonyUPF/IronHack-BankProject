package com.ironhack.demosecurityjwt.repositories.UserBankRepository;


import com.ironhack.demosecurityjwt.models.UserBank.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
}
