package com.ironhack.demosecurityjwt.services.interfaces;

import com.ironhack.demosecurityjwt.dtos.BlogPostDTO;
import com.ironhack.demosecurityjwt.models.BlogPost;
import java.util.List;

public interface BlogPostServiceInterface {

    List<BlogPost> getAllBlogPosts();

    BlogPost getPostAndAuthor(Integer id);
    BlogPost addPost(BlogPostDTO blogPostDTO);

    BlogPost updatePost(Integer id, BlogPostDTO blogPostDTO);

    void deletePost(Integer id);

}
