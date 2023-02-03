package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPost;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import com.ironhack.demosecurityjwt.repositories.BlogPostRepository;
import com.ironhack.demosecurityjwt.services.interfaces.AuthorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorService implements AuthorServiceInterface {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BlogPostRepository blogPostRepository;


    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author addAuthor(String author) {
        return authorRepository.save(new Author(author));
    }

    @Override
    public Author updateAuthor(Integer id, String name) {
        Author author=authorRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "The author id does not exist in the database"));
        author.setName(name);
        return  authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Integer id) {
        Author author=authorRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "The author id does not exist in the database"));

        List<BlogPost> blogPostList =blogPostRepository.findByAuthorId(author);
        blogPostRepository.deleteAll(blogPostList);
        authorRepository.delete(author);
        System.err.println("The author with id: "+ id +" was successfully deleted from the database");
    }
}
