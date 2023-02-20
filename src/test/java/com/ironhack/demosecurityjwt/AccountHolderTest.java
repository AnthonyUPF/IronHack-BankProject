package com.ironhack.demosecurityjwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.ChangeBalanceDTO;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.CreditCardDTO;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.SavingsDTO;
import com.ironhack.demosecurityjwt.models.Account.Account;
import com.ironhack.demosecurityjwt.models.Account.CreditCard;
import com.ironhack.demosecurityjwt.models.Account.Savings;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankUser.AccountHolder;
import com.ironhack.demosecurityjwt.models.BankUser.Admin;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.AccountRepository;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.CreditCardRepository;
import com.ironhack.demosecurityjwt.repositories.AccountRepository.SavingsRepository;
import com.ironhack.demosecurityjwt.repositories.AddressRepository.AddressRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AccountHolderRepository;
import com.ironhack.demosecurityjwt.repositories.BankUserRepository.AdminRepository;
import com.ironhack.demosecurityjwt.repositories.UserRepository;
import com.ironhack.demosecurityjwt.services.BankUserService.AdminService;
import com.ironhack.demosecurityjwt.services.impl.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHolderTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private MvcResult mvcResult;

    private ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;




    Address address;

    AccountHolder accountHolder;

    Admin admin;



    Savings savings;

    CreditCard creditCard;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        address=addressRepository.save(
                new Address(
                        "street test",
                        "city test",
                        "state test",
                        "00001")
        );
        accountHolder=accountHolderRepository.save(
                new AccountHolder(
                        "Account Holder Test",
                        "accountHolderTest",
                        "1234",
                        LocalDate.of(1890,10,5),
                        address
                )
        );

        admin=adminRepository.save(
                new Admin(
                        "Admin Test",
                        "adminTest",
                        "1234"
                )
        );


        savings=savingsRepository.save(
                new Savings(
                        new Money(new BigDecimal(200)),
                        accountHolder
                )
        );

        creditCard=creditCardRepository.save(
                new CreditCard(
                        new Money(new BigDecimal(200)),
                        accountHolder
                )
        );

        userService.addRoleToUser("accountHolderTest", "ROLE_ACCOUNT_HOLDER");
        userService.addRoleToUser("adminTest", "ROLE_ADMIN");

    }

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
        accountRepository.deleteAll();
    }



    @Test
    public void getAllPrimaryOwnAccounts_successTest() throws Exception {
        mvcResult = mockMvc.perform(get("/api/bankUsers/accountHolders/primaryOwnAccounts")
                        .with(user("accountHolderTest").password("1234")))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public void getAllSecondaryOwnAccounts_successTest() throws Exception {
        mvcResult = mockMvc.perform(get("/api/bankUsers/accountHolders/secondaryOwnAccounts")
                        .with(user("accountHolderTest").password("1234")))
                .andExpect(status().isOk()).andReturn();
    }




/*

    @Test
    public void addAuthor_successTest() throws Exception {
        String newAuthor=author.getName();
        mvcResult = mockMvc.perform(post("/api/authors/"+newAuthor))
                .andExpect(status().isCreated()).andReturn();

        Author author1 = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Author.class);
        assertEquals(author.getName(), author1.getName());
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

*/

}
