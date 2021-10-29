package com.ironhack.accountservice.dao;

import com.ironhack.accountservice.exceptions.ExceedsMaxLength;
import com.ironhack.accountservice.exceptions.InvalidCountryException;
import com.ironhack.accountservice.exceptions.NameContainsNumbersException;
import com.ironhack.accountservice.AccountServiceApplication;
import com.ironhack.accountservice.enums.Industry;
import com.ironhack.accountservice.exceptions.EmptyStringException;
import com.ironhack.accountservice.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountTest {

    @MockBean
    private AccountServiceApplication application;

    @Autowired
    private AccountRepository accountRepository;

    private Account acc;
    private Account acc1;
    private Account acc2;


    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        acc = new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM");
        acc1= new Account(Industry.ECOMMERCE, 500, "Madrid", "SPAIN");
        acc2= new Account(Industry.MANUFACTURING, 20, "Paris", "FRANCE");
        List<Account> accounts = accountRepository.saveAll(List.of(acc, acc1, acc2));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void setEmployeeCount_shouldWork() throws ExceedsMaxLength {
        acc.setEmployeeCount(2);
        assertEquals(2, acc.getEmployeeCount());
    }

    @Test
    void setEmployeeCount_throwsException_IllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> { acc.setEmployeeCount(0);});
    }

    @Test
    void setCity_shouldWork() throws EmptyStringException, NameContainsNumbersException, ExceedsMaxLength {
        acc.setCity("Dubai");
        assertEquals("Dubai", acc.getCity());
    }

    @Test
    void setCity_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { acc.setCity("");});
    }


    @Test
    void setCity_throwsException_ContainsNumbers() {
        assertThrows(NameContainsNumbersException.class, () -> { acc.setCity("Madr1d");});
    }


    @Test
    void setCity_throwsException_ExceedsMaxLength() {
        assertThrows(ExceedsMaxLength.class, () -> { acc.setCity("Longestnameeverseenonacity");});
    }

    @Test
    void setCountry_shouldWork() throws EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        acc.setCountry("SPAIN");
        assertEquals("SPAIN", acc.getCountry());
    }


    @Test
    void setCountry_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { acc.setCountry("");});
    }

    @Test
    void setCountry_throwsException_ExceedsMaxLength() {
        assertThrows(ExceedsMaxLength.class, () -> { acc.setCountry("Longest country name really really long");});
    }

    @Test
    void setCountry_throwsException_InvalidCountry() {
        assertThrows(InvalidCountryException.class, () -> { acc.setCountry("Japun");});
    }
}