package com.tkwrn.accountservice.repository;

import com.tkwrn.accountservice.AccountServiceApplication;
import com.tkwrn.accountservice.dao.Account;
import com.tkwrn.accountservice.enums.Industry;
import com.tkwrn.accountservice.exceptions.EmptyStringException;
import com.tkwrn.accountservice.exceptions.ExceedsMaxLength;
import com.tkwrn.accountservice.exceptions.InvalidCountryException;
import com.tkwrn.accountservice.exceptions.NameContainsNumbersException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @MockBean
    private AccountServiceApplication application;

    @Autowired
    private AccountRepository accountRepository;

    private List<Account> accounts;

    private Account acc;
    private Account acc1;
    private Account acc2;


    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        acc = new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM");
        acc1= new Account(Industry.ECOMMERCE, 500, "Madrid", "SPAIN");
        acc2= new Account(Industry.MANUFACTURING, 20, "Paris", "FRANCE");
        accounts = accountRepository.saveAll(List.of(acc, acc1, acc2));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void findMeanEmployeeCount() {
        var meanEmployeeCount = accountRepository.findMeanEmployeeCount();
        assertEquals(190, meanEmployeeCount.get().doubleValue());
    }

    @Test
    void findMedianEmployeeCountStep1_test(){
        var medianEmployeeCount = accountRepository.findMedianEmployeeCountStep1();
        assertEquals(3, medianEmployeeCount.length);
    }

    @Test
    void findMaxEmployeeCount() {
        var maxEmployeeCount = accountRepository.findMaxEmployeeCount();
        assertEquals(500, maxEmployeeCount.get().intValue());
    }


    @Test
    void findMinEmployeeCount() {
        var minEmployeeCountEmployeeCount = accountRepository.findMinEmployeeCount();
        assertEquals(20, minEmployeeCountEmployeeCount.get().intValue());
    }
}