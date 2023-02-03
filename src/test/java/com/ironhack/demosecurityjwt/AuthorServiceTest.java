package com.ironhack.demosecurityjwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPost;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import com.ironhack.demosecurityjwt.repositories.BlogPostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthorServiceTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private MvcResult mvcResult;

    private ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AuthorRepository authorRepository;



    BlogPost blogPost;
    Author author;

    @BeforeEach
    void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        author=new Author("AuthorTest");
        blogPost=new BlogPost(author,"BlogTest","BlogPostTest");

        authorRepository.save(author);
        blogPostRepository.save(blogPost);
    }

    @AfterEach
    void tearDown(){
        blogPostRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    public void getAllAuthors_successTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/authors"))
                .andExpect(status().isOk()).andReturn();
        List<Author> authorList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(authorRepository.findAll().size(), authorList.size());
    }




    @Test
    public void deleteAuthor_successTest() throws Exception {
        mvcResult = mockMvc.perform(delete("/api/authors/delete/" + author.getId()))
                .andExpect(status().isOk()).andReturn();
        assertFalse(authorRepository.findById(blogPost.getId()).isPresent());
    }

    @Test
    public void deleteAuthor_failureTest() throws Exception {
        mvcResult = mockMvc.perform(delete("/api/authors/delete/" + author.getId()+1))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains("id does not exist in the database"));
    }



}
