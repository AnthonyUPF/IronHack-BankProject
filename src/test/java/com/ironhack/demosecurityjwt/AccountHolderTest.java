package com.ironhack.demosecurityjwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.demosecurityjwt.Enuns.ChangeBalance;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.AddressDTO;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.ChangeBalanceDTO;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.CreditCardDTO;
import com.ironhack.demosecurityjwt.dtos.AccountDTO.SavingsDTO;
import com.ironhack.demosecurityjwt.dtos.BankUserDTO.AccountHolderDTO;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminTest {
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
    public void getAllSavings_successTest() throws Exception {
        mvcResult = mockMvc.perform(get("/api/bankUsers/admins/savings")
                        .with(user("adminTest").password("1234")))
                .andExpect(status().isOk()).andReturn();
        List<Savings> savingsList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(savingsRepository.findAll().size(), savingsList.size());
    }

    @Test
    public void getAllSavings_failureTest() throws Exception {
        mvcResult = mockMvc.perform(get("/api/bankUsers/admins/savings")
                        .with(user("accountHolderTest").password("1234")))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void getAllCreditCards_successTest() throws Exception {
        mvcResult = mockMvc.perform(get("/api/bankUsers/admins/creditCards")
                        .with(user("adminTest").password("1234")))
                .andExpect(status().isOk()).andReturn();
        List<CreditCard>creditCardList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(creditCardRepository.findAll().size(), creditCardList.size());
    }

    @Test
    public void getAllCreditCards_failureTest() throws Exception {
        mvcResult = mockMvc.perform(get("/api/bankUsers/admins/creditCards")
                        .with(user("accountHolderTest").password("1234")))
                .andExpect(status().isBadRequest()).andReturn();
    }



    @Test
    public void getAllAdmins_successTest() throws Exception {
        mvcResult = mockMvc.perform(get("/api/bankUsers/admins/getAdmins")
                        .with(user("adminTest").password("1234")))
                .andExpect(status().isOk()).andReturn();
        List<Admin>adminList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(adminRepository.findAll().size(), adminList.size());
    }

    @Test
    public void getAllAdmins_failureTest() throws Exception {
        mvcResult = mockMvc.perform(get("/api/bankUsers/admins/getAdmins")
                        .with(user("accountHolderTest").password("1234")))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void changeBalanceOfAnyAccount_successTest() throws Exception {

         Account testAccount= accountRepository.save(
                 new Account(
                         new Money(new BigDecimal("100"))
                 )
         );

        ChangeBalanceDTO changeBalanceDTO = new ChangeBalanceDTO(testAccount.getAccountId(), 100);
        String requestBody = objectMapper.writeValueAsString(changeBalanceDTO);

        mvcResult = mockMvc.perform(post("/api/bankUsers/admins/changeBalanceOfAccounts")
                        .with(user("adminTest").password("1234"))
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("changeBalance", "INCREASE_BALANCE"))
                .andExpect(status().isAccepted())
                .andReturn();

        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals( json.getJSONObject("balance").get("amount"), accountRepository.findById(testAccount.getAccountId()).get().getBalance().getAmount().doubleValue() );


    }


    @Test
    public void changeBalanceOfAnyAccount_failureTest() throws Exception {

        Account testAccount= accountRepository.save(
                new Account(
                        new Money(new BigDecimal("100"))
                )
        );

        ChangeBalanceDTO changeBalanceDTO = new ChangeBalanceDTO(testAccount.getAccountId(), 100);
        String requestBody = objectMapper.writeValueAsString(changeBalanceDTO);

        mvcResult = mockMvc.perform(post("/api/bankUsers/admins/changeBalanceOfAccounts")
                        .with(user("accountHolderTest").password("1234"))
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("changeBalance", "INCREASE_BALANCE"))
                .andExpect(status().isBadRequest())
                .andReturn();

    }


    @Test
    public void createSavings_successTest() throws Exception {
        SavingsDTO savingsDTO = new SavingsDTO(new BigDecimal("1000"), 1, new BigDecimal("100"), new BigDecimal("0.01"));
        String requestBody = objectMapper.writeValueAsString(savingsDTO);

        mvcResult=mockMvc.perform(post("/api/bankUsers/admins/newSavings")
                        .with(user("adminTest").password("1234"))
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertTrue(savingsRepository.findById(Integer.valueOf(json.getString("accountId"))).isPresent()  );
    }

    @Test
    public void createSavings_failureTest() throws Exception {
        SavingsDTO savingsDTO = new SavingsDTO(new BigDecimal("1000"), 1, new BigDecimal("100"), new BigDecimal("0.01"));
        String requestBody = objectMapper.writeValueAsString(savingsDTO);

        mvcResult=mockMvc.perform(post("/api/bankUsers/admins/newSavings")
                        .with(user("accountHolderTest").password("1234"))
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

    }



    @Test
    public void createCreditCard_successTest() throws Exception {
        CreditCardDTO creditCardDTO = new CreditCardDTO(new BigDecimal("100"), 1, new BigDecimal("1000"), new BigDecimal("0.2"));

        String requestBody = objectMapper.writeValueAsString(creditCardDTO);

        mvcResult = mockMvc.perform(post("/api/bankUsers/admins/newCreditCard")
                        .with(user("adminTest").password("1234"))
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertTrue(creditCardRepository.findById(Integer.valueOf(json.getString("accountId"))).isPresent()  );


    }




    @Test
    public void createCreditCard_failureTest() throws Exception {
        CreditCardDTO creditCardDTO = new CreditCardDTO(new BigDecimal("100"), 1, new BigDecimal("1000"), new BigDecimal("0.2"));

        String requestBody = objectMapper.writeValueAsString(creditCardDTO);

        mvcResult = mockMvc.perform(post("/api/bankUsers/admins/newCreditCard")
                        .with(user("accountHolderTest").password("1234"))
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

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
