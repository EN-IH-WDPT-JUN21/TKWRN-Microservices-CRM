package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.dto.ContactRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactRequestDTOTest {

    ContactRequestDTO contactRequestDTO1;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength, EmailNotValidException, PhoneNumberContainsLettersException {
        contactRequestDTO1 = new ContactRequestDTO("TestOne", "123546", "test1@test.gmail.com", "TestCompany1");
    }

    @Test
    void setName_shouldWork() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
        contactRequestDTO1.setName("Carlos Vasquéz");
        assertEquals("Carlos Vasquéz", contactRequestDTO1.getName());
    }

    @Test
    void setName_throwsException_EmptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> { contactRequestDTO1.setName("");});
    }

    @Test
    void setName_throwsException_ContainsNumbers() {
        Assert.assertThrows(NameContainsNumbersException.class, () -> { contactRequestDTO1.setName("Lee1");});
    }

    @Test
    void setName_throwsException_ExceedsMaxCharacters() {
        Assert.assertThrows(ExceedsMaxLength.class, () -> { contactRequestDTO1.setName("Arbitrary Name" +
                "to test if the name exceeds the max length of forty three characters");});
    }


    @Test
    void setPhoneNumber_shouldWork() throws EmptyStringException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        contactRequestDTO1.setPhoneNumber("123456");
        assertEquals("123456", contactRequestDTO1.getPhoneNumber());

    }

    @Test
    void setPhoneNumber_throwsException_EmptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> { contactRequestDTO1.setPhoneNumber("");});
    }

    @Test
    void setPhoneNumber_throwsException_ContainsNumbers() {
        Assert.assertThrows(PhoneNumberContainsLettersException.class, () -> { contactRequestDTO1.setPhoneNumber("123a56");});
    }

    @Test
    void setPhoneNumber_throwsException_ExceedsMaxCharacters() {
        Assert.assertThrows(ExceedsMaxLength.class, () -> { contactRequestDTO1.setPhoneNumber("12345678901234567890123");});
    }

    @Test
    void setEmail_shouldWork() throws EmptyStringException, EmailNotValidException, ExceedsMaxLength {
        contactRequestDTO1.setEmail("contact1@mail.com");
        assertEquals("contact1@mail.com", contactRequestDTO1.getEmail());
    }

    @Test
    void setEmail_throwsException_EmptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> { contactRequestDTO1.setEmail("");});
    }

    @Test
    void setEmail_throwsException_InvalidEmail() {

        //Wrong Format - Missing @
        Assert.assertThrows(EmailNotValidException.class, () -> { contactRequestDTO1.setEmail("contact1mail.com");});

        //Wrong Format - Missing .
        Assert.assertThrows(EmailNotValidException.class, () -> { contactRequestDTO1.setEmail("contact1@mailcom");});

        // Invalid TLD
        Assert.assertThrows(EmailNotValidException.class, () -> { contactRequestDTO1.setEmail("contact1@mail.crom");});
    }

    @Test
    void setEmail_throwsException_ExceedsMaxCharacters() {
        Assert.assertThrows(ExceedsMaxLength.class, () -> { contactRequestDTO1.setEmail("lead1withalotofcharactersfortesting@mail.com");});
    }

    @Test
    void setCompanyName_shouldWork() throws EmptyStringException, ExceedsMaxLength {
        contactRequestDTO1.setCompanyName("Company of Contact 1");
        assertEquals("Company of Contact 1", contactRequestDTO1.getCompanyName());
    }


    @Test
    void setCompanyName_throwsException_EmptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> { contactRequestDTO1.setCompanyName("");});
    }

    @Test
    void setCompanyName_throwsException_ExceedsMaxCharacters() {
        Assert.assertThrows(ExceedsMaxLength.class, () -> { contactRequestDTO1.setCompanyName("This is an extra long company" +
                "name with some extra characters");});
    }


}