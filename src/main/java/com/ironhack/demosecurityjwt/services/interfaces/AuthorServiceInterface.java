package com.ironhack.demosecurityjwt.services.interfaces;

import com.ironhack.demosecurityjwt.models.Author;
import java.util.List;

public interface AuthorServiceInterface {

    List<Author> getAllAuthors();

    Author addAuthor(String author);

    Author updateAuthor(Integer id, String name);

    void deleteAuthor(Integer id);


}
