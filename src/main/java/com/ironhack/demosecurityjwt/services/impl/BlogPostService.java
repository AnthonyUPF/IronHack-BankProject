package com.ironhack.demosecurityjwt.services.impl;


import com.ironhack.demosecurityjwt.dtos.BlogPostDTO;
import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPost;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import com.ironhack.demosecurityjwt.repositories.BlogPostRepository;
import com.ironhack.demosecurityjwt.services.interfaces.BlogPostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BlogPostService implements BlogPostServiceInterface{
    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AuthorRepository authorRepository;



    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost getPostAndAuthor(Integer id) {
        return  blogPostRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "The blogpost id does not exist in the database"));
    }

    @Override
    public BlogPost addPost(BlogPostDTO blogPostDTO) {
        Author author= authorRepository.findById(blogPostDTO.getAuthorId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "The author id does not exist in the database"));
        BlogPost blogPost=new BlogPost(author,blogPostDTO.getTitle(),blogPostDTO.getPost());
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost updatePost(Integer id, BlogPostDTO blogPostDTO) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "The blogpost id does not exist in the database"));

        Author author = authorRepository.findById(blogPostDTO.getAuthorId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "The author id does not exist in the database"));

        blogPost.setAuthorId(author);
        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setPost(blogPostDTO.getPost());
        return  blogPostRepository.save(blogPost);
    }

    @Override
    public void deletePost(Integer id) {
        BlogPost blogPost=blogPostRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "The blogpost id does not exist in the database"));;
        blogPostRepository.delete(blogPost);
        System.err.println("The blogpost with id: "+ id +" was successfully deleted from the database");
    }
}
