package com.ironhack.demosecurityjwt.repositories.UserBankRepository;


import com.ironhack.demosecurityjwt.models.UserBank.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Integer> {
}
