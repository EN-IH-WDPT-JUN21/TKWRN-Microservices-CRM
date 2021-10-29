package com.ironhack.accountservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.accountservice.AccountServiceApplication;
import com.ironhack.accountservice.controller.dto.AccountRequestDTO;
import com.ironhack.accountservice.controller.dto.ContactRequestDTO;
import com.ironhack.accountservice.controller.dto.OpportunityRequestDTO;
import com.ironhack.accountservice.controller.dto.SalesRepDTO;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private List<ContactRequestDTO> contacts= new ArrayList<>();
    private List<OpportunityRequestDTO> opportunities= new ArrayList<>();
    private List<Account> accounts;

    private Account acc;
    private Account acc1;
    private Account acc2;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        acc = new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM");
        acc1= new Account(Industry.ECOMMERCE, 500, "Madrid", "SPAIN");
        acc2= new Account(Industry.MANUFACTURING, 20, "Paris", "FRANCE");
        accounts = accountRepository.saveAll(List.of(acc, acc1, acc2));

        salesReps = List.of(
                new SalesRepDTO(1l, "David Lynch"),
                new SalesRepDTO(2l, "Martha Stewart")
        );

        contacts = List.of(
                new ContactRequestDTO(1l, "John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", 1l
                        ),
                new ContactRequestDTO(2l, "Martha Steward", "123475357", "ms@wp.pl",
                        "My own company", 1l),
                new ContactRequestDTO(3l, "George Truman", "123475357", "thisisverylongemail@gmail.com",
                        "Truman Show", 2l)

        );

        opportunities = List.of(
                new OpportunityRequestDTO(1l, Status.OPEN, Truck.FLATBED, 10, 1l, 1l),
                new OpportunityRequestDTO(2l, Status.OPEN, Truck.BOX, 1150, 2l, 1l),
                new OpportunityRequestDTO(3l, Status.OPEN, Truck.HYBRID, 1, 3l, 2l)

        );


    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void getAccounts() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts")).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("London"));
        assertTrue(result.getResponse().getContentAsString().contains("SPAIN"));
        assertTrue(result.getResponse().getContentAsString().contains("Paris"));
    }

    @Test
    void findAccountById() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/accounts/"+acc2.getId())
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("FRANCE"));
    }

    @Test
    void create() throws Exception {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO("MEDICAL", 100, "Warsaw", "POLAND");
        String body = objectMapper.writeValueAsString(accountRequestDTO);
        MvcResult result = mockMvc.perform(post("/accounts").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Warsaw"));
    }

//    can't test w/o other services
//    @Test
//    void createAccount() throws Exception {
//        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(1l, "MEDICAL", 100, "Warsaw", "POLAND");
//        String body = objectMapper.writeValueAsString(accountRequestDTO);
//        MvcResult result = mockMvc.perform(post("/accounts/new").content(body)
//                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated()).andReturn();
//        assertTrue(result.getResponse().getContentAsString().contains("Warsaw"));
//    }

    @Test
    void updateAccount() {
    }

    @Test
    void populate() throws Exception {
        var accountCount0 = accountRepository.findAll().size();
        MvcResult result = mockMvc.perform(get("/accounts/populate")).andDo(print()).andExpect(status().isOk()).andReturn();
        var accountCount1 = accountRepository.findAll().size();
        assertEquals(3, accountCount1-accountCount0);
    }

    @Test
    void findMeanEmployeeCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/reports/MEAN-EMPLOYEE-COUNT")).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("190"));
    }

    @Test
    void findMedianEmployeeCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/reports/MEDIAN-EMPLOYEE-COUNT")).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("50"));
    }

    @Test
    void findMaxEmployeeCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/reports/MAX-EMPLOYEE-COUNT")).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("500"));
    }

    @Test
    void findMinEmployeeCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/reports/MIN-EMPLOYEE-COUNT")).andDo(print()).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("20"));
    }
}