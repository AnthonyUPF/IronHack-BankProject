package com.ironhack.demosecurityjwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.demosecurityjwt.dtos.BlogPostDTO;
import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPost;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import com.ironhack.demosecurityjwt.repositories.BlogPostRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.mysql.cj.exceptions.MysqlErrorNumbers.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BlogPostServiceTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private MvcResult  mvcResult;

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
    public void getAllBlogPosts_successTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/blogPosts"))
                .andExpect(status().isOk()).andReturn();
        List<BlogPost> blogPostList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(blogPostRepository.findAll().size(), blogPostList.size());
    }


    @Test
    public void getPostAndAuthor_successTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/blogPosts/"+blogPost.getId()))
                .andExpect(status().isOk()).andReturn();
        BlogPost blogPos1 = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BlogPost.class);
        assertEquals(blogPost.getAuthorId().getName(), blogPos1.getAuthorId().getName());
    }

    @Test
    public void getPostAndAuthor_failureTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/blogPosts/"+blogPost.getId()+1))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains("id does not exist in the database"));
    }



    @Test
    public void addPost_successTest() throws Exception {
        BlogPostDTO product = new BlogPostDTO(1, "Test", "BlogPostTest");
        String body = objectMapper.writeValueAsString(product);

        MvcResult mvcResult = mockMvc.perform(post("/api/blogPosts/new")
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test"));

        assertEquals(product.getTitle(), blogPostRepository.findByTitle(product.getTitle()).getTitle());
    }


    @Test
    public void addPost_failureTest() throws Exception {
        BlogPostDTO product = new BlogPostDTO(2, "Test", "BlogPostTest");
        String body = objectMapper.writeValueAsString(product);

        MvcResult mvcResult = mockMvc.perform(put("/api/blogPosts/"+blogPost.getId())
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResolvedException().getMessage().contains("id does not exist in the database"));
    }





    @Test
    public void updatePost_successTest() throws Exception {
        BlogPostDTO product = new BlogPostDTO(1, "Test", "BlogPostTest");
        String body = objectMapper.writeValueAsString(product);

        MvcResult mvcResult = mockMvc.perform(post("/api/blogPosts/new")
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test"));

        assertEquals(product.getTitle(), blogPostRepository.findByTitle(product.getTitle()).getTitle());
    }






    @Test
    public void deletePost_successTest() throws Exception {
        mvcResult = mockMvc.perform(delete("/api/blogPosts/delete/" + blogPost.getId()))
                .andExpect(status().isOk()).andReturn();
        assertFalse(blogPostRepository.findById(blogPost.getId()).isPresent());
    }

    @Test
    public void deletePost_failureTest() throws Exception {
        mvcResult = mockMvc.perform(delete("/api/blogPosts/delete/" + blogPost.getId()+1))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains("id does not exist in the database"));
    }





}
