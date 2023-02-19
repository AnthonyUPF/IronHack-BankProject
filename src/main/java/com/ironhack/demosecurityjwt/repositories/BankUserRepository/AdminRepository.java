package com.ironhack.demosecurityjwt.repositories.UserBankRepository;


import com.ironhack.demosecurityjwt.models.UserBank.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
