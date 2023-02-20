package com.ironhack.demosecurityjwt.repositories.AccountRepository;



import com.ironhack.demosecurityjwt.models.Account.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings,Integer> {
}
