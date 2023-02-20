package com.ironhack.demosecurityjwt.repositories.AccountRepository;


import com.ironhack.demosecurityjwt.models.Account.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {
}
