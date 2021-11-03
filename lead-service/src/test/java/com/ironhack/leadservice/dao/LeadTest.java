package com.ironhack.leadservice.dao;

import com.ironhack.leadservice.exceptions.*;
import com.ironhack.leadservice.repository.LeadRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LeadTest {

    @Autowired
    private LeadRepository leadRepository;

    private Lead lead1;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength, EmailNotValidException, PhoneNumberContainsLettersException {
        lead1 = new Lead("TestOne", "123546", "test1@test.gmail.com", "TestCompany1");
        leadRepository.save(lead1);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void setName_shouldWork() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
        lead1.setName("Carlos Vasquéz");
        assertEquals("Carlos Vasquéz", lead1.getName());

    }

    @Test
    void setName_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { lead1.setName("");});
    }

    @Test
    void setName_throwsException_ContainsNumbers() {
        assertThrows(NameContainsNumbersException.class, () -> { lead1.setName("Lee1");});
    }

    @Test
    void setName_throwsException_ExceedsMaxCharacters() {
        assertThrows(ExceedsMaxLength.class, () -> { lead1.setName("Arbitrary Name" +
                "to test if the name exceeds the max length of forty three characters");});
    }


    @Test
    void setPhoneNumber_shouldWork() throws EmptyStringException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        lead1.setPhoneNumber("123456");
        assertEquals("123456", lead1.getPhoneNumber());

    }

    @Test
    void setPhoneNumber_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { lead1.setPhoneNumber("");});
    }

    @Test
    void setPhoneNumber_throwsException_ContainsNumbers() {
        assertThrows(PhoneNumberContainsLettersException.class, () -> { lead1.setPhoneNumber("123a56");});
    }

    @Test
    void setPhoneNumber_throwsException_ExceedsMaxCharacters() {
        assertThrows(ExceedsMaxLength.class, () -> { lead1.setPhoneNumber("12345678901234567890123");});
    }

    @Test
    void setEmail_shouldWork() throws EmptyStringException, EmailNotValidException, ExceedsMaxLength {
        lead1.setEmail("lead2@mail.com");
        assertEquals("lead2@mail.com", lead1.getEmail());
    }

    @Test
    void setEmail_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { lead1.setEmail("");});
    }

    @Test
    void setEmail_throwsException_InvalidEmail() {

        //Wrong Format - Missing @
        assertThrows(EmailNotValidException.class, () -> { lead1.setEmail("lead1mail.com");});

        //Wrong Format - Missing .
        assertThrows(EmailNotValidException.class, () -> { lead1.setEmail("lead1@mailcom");});

        // Invalid TLD
        assertThrows(EmailNotValidException.class, () -> { lead1.setEmail("lead1@mail.crom");});
    }

    @Test
    void setEmail_throwsException_ExceedsMaxCharacters() {
        assertThrows(ExceedsMaxLength.class, () -> { lead1.setEmail("lead1withalotofcharactersfortesting@mail.com");});
    }

    @Test
    void setCompanyName_shouldWork() throws EmptyStringException, ExceedsMaxLength {
        lead1.setCompanyName("Company of Lead 1");
        assertEquals("Company of Lead 1", lead1.getCompanyName());
    }


    @Test
    void setCompanyName_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { lead1.setCompanyName("");});
    }

    @Test
    void setCompanyName_throwsException_ExceedsMaxCharacters() {
        assertThrows(ExceedsMaxLength.class, () -> { lead1.setCompanyName("This is an extra long company" +
                "name with some extra characters");});
    }

}
