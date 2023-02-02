package com.ironhack.demosecurityjwt.dtos;

import com.ironhack.demosecurityjwt.models.Author;

public class BlogPostDTO {
    private Integer authorId;

    private String title;
    private String post;

    public BlogPostDTO(Integer authorId, String title, String post) {
        this.authorId = authorId;
        this.title = title;
        this.post = post;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
