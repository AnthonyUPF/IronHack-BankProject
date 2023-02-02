package com.ironhack.demosecurityjwt.controllers.impl;

import com.ironhack.demosecurityjwt.dtos.BlogPostDTO;
import com.ironhack.demosecurityjwt.models.BlogPost;
import com.ironhack.demosecurityjwt.repositories.BlogPostRepository;
import com.ironhack.demosecurityjwt.services.impl.BlogPostService;
import com.ironhack.demosecurityjwt.services.interfaces.BlogPostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogPostController implements BlogPostServiceInterface {

    @Autowired
    BlogPostService blogPostService;



    @GetMapping("/blogPosts")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @GetMapping("/blogPosts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogPost getPostAndAuthor(@PathVariable Integer id) {
        return blogPostService.getPostAndAuthor(id);
    }

    @PostMapping("/blogPosts/new")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost addPost(@RequestBody BlogPostDTO blogPostDTO) {
        return blogPostService.addPost(blogPostDTO);
    }

    @PutMapping("/blogPosts/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BlogPost updatePost(@PathVariable Integer id,@RequestBody BlogPostDTO blogPostDTO) {
        return blogPostService.updatePost(id,blogPostDTO);
    }

    @DeleteMapping("/blogPosts/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Integer id) {
        blogPostService.deletePost(id);
    }
}
