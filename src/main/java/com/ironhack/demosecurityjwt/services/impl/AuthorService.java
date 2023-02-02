package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import com.ironhack.demosecurityjwt.services.interfaces.AuthorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements AuthorServiceInterface {
    @Autowired
    AuthorRepository authorRepository;


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
        Author author=authorRepository.findById(id).get();
        author.setName(name);
        return  authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Integer id) {
        Author author=authorRepository.findById(id).get();
        authorRepository.delete(author);
    }
}
