package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
