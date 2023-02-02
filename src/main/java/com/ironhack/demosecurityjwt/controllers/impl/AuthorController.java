package com.ironhack.demosecurityjwt.controllers.impl;


import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import com.ironhack.demosecurityjwt.services.impl.AuthorService;
import com.ironhack.demosecurityjwt.services.interfaces.AuthorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController implements AuthorServiceInterface {
    @Autowired
    AuthorService authorService;


    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("/authors/{author}")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@PathVariable String author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("/authors/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(@PathVariable Integer id,@RequestParam String name) {
        return authorService.updateAuthor(id,name);
    }

    @DeleteMapping("/authors/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
    }
}
