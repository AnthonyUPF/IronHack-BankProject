package com.ironhack.demosecurityjwt.repositories.TransactionRepository;

import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
