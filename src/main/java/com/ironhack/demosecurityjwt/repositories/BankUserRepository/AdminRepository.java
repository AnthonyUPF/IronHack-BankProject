package com.ironhack.demosecurityjwt.repositories.BankUserRepository;


import com.ironhack.demosecurityjwt.models.BankUser.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);
}
