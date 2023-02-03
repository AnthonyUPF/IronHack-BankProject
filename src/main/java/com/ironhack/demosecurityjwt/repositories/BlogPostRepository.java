package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost,Integer> {

    List<BlogPost> findByAuthorId(Author authorId);
    BlogPost findByTitle(String title);
}
