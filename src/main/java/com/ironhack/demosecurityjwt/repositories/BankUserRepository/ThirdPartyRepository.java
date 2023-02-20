package com.ironhack.demosecurityjwt.repositories.BankUserRepository;


import com.ironhack.demosecurityjwt.models.BankUser.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Integer> {
    ThirdParty findByHashedKey(String hashedKey);

}
