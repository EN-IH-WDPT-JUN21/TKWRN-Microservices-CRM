package com.ironhack.accountservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.accountservice.AccountServiceApplication;
import com.ironhack.accountservice.controller.dto.*;
import com.ironhack.accountservice.dao.Account;
import com.ironhack.accountservice.enums.Industry;
import com.ironhack.accountservice.enums.Status;
import com.ironhack.accountservice.enums.Truck;
import com.ironhack.accountservice.exceptions.EmptyStringException;
import com.ironhack.accountservice.exceptions.ExceedsMaxLength;
import com.ironhack.accountservice.exceptions.InvalidCountryException;
import com.ironhack.accountservice.exceptions.NameContainsNumbersException;
import com.ironhack.accountservice.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AccountServiceApplication application;

    @Autowired
    private AccountRepository accountRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<SalesRepDTO> salesReps = new ArrayList<>();
    private List<ContactReceiptDTO> contacts= new ArrayList<>();
    private List<OpportunityReceiptDTO> opportunities= new ArrayList<>();
    private List<Account> accounts;

    private Account acc;
    private Account acc1;
    private Account acc2;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        acc = new Account(Industry.PRODUCE, 50, "Warsaw", "Poland");
        acc1= new Account(Industry.ECOMMERCE, 500, "Malaga", "SPAIN");
        acc2= new Account(Industry.MANUFACTURING, 20, "Rome", "ITALY");
        accounts = accountRepository.saveAll(List.of(acc, acc1, acc2));

        salesReps = List.of(
                new SalesRepDTO(1l, "Paul Newman"),
                new SalesRepDTO(2l, "Angelina Jolie")
        );

        contacts = List.of(
                new ContactReceiptDTO(1l, "John Doe", "123475357", "alfa@beta.uk",
                        "Kałasznikow", 1l,acc.getId()),
                new ContactReceiptDTO(2l, "Martha Steward", "123475357", "ms@wp.pl",
                        "My own company", 1l, acc1.getId()),
                new ContactReceiptDTO(3l, "George Truman", "123475357", "thisisverylongemail@gmail.com",
                        "Truman Show", 2l, acc2.getId())

        );

        opportunities = List.of(
                new OpportunityReceiptDTO(1l, Status.OPEN, Truck.FLATBED, 10, 1l, acc.getId(), 1l),
                new OpportunityReceiptDTO(2l, Status.OPEN, Truck.BOX, 1150, 2l, acc1.getId(), 1l),
                new OpportunityReceiptDTO(3l, Status.OPEN, Truck.HYBRID, 1, 3l, acc2.getId(), 2l)

        );


    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void getAccounts() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/accounts")).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Warsaw"));
        assertTrue(result.getResponse().getContentAsString().contains("Malaga"));
        assertTrue(result.getResponse().getContentAsString().contains("Rome"));
    }

    @Test
    void findAccountById() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/api/v1/accounts/"+acc2.getId())
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("ITALY"));
    }

    @Test
    void create() throws Exception {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO("MEDICAL", 100, "Poznan", "POLAND");
        String body = objectMapper.writeValueAsString(accountRequestDTO);
        MvcResult result = mockMvc.perform(post("/api/v1/accounts").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Poznan"));
    }


//    WARNING: can't test w/o other services - have to running and with sample data matching
    @Test
    void createAccount() throws Exception {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(1l, "MEDICAL", 100, "Wroclaw", "POLAND");
        String body = objectMapper.writeValueAsString(accountRequestDTO);
        MvcResult result = mockMvc.perform(post("/api/v1/accounts/new").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Wroclaw"));
    }

    @Test
    void createAccountFallback() throws Exception {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(1000l, "MEDICAL", 100, "Wroclaw", "POLAND");
        String body = objectMapper.writeValueAsString(accountRequestDTO);
        MvcResult result = mockMvc.perform(post("/api/v1/accounts/new").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("fallback"));
    }
    //    WARNING: can't test w/o other services - have to running and with sample data matching
    @Test
    void updateAccount() throws Exception {
        AccountUpdateDTO accountUpdateDTO = new AccountUpdateDTO(1l);
        String body = objectMapper.writeValueAsString(accountUpdateDTO);
        MvcResult result = mockMvc.perform(put("/api/v1/accounts/change/" + acc.getId()).content(body)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Warsaw"));
    }

    @Test
    void updateAccountFallback() throws Exception {
        AccountUpdateDTO accountUpdateDTO = new AccountUpdateDTO(1000l);
        String body = objectMapper.writeValueAsString(accountUpdateDTO);
        MvcResult result = mockMvc.perform(put("/api/v1/accounts/change/" + acc.getId()).content(body)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("fallback"));
    }

}