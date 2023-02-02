package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost,Integer> {
}
