package com.ironhack.demosecurityjwt.services.interfaces;

import com.ironhack.demosecurityjwt.models.Author;

public interface AunthorServiceInterface {

    Author addAuthor(String author);

    Author updateAuthor(Integer id, String name);

    void deleteAuthor(Integer id);


}
