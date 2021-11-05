package com.ironhack.contactservice.dao;

import com.ironhack.contactservice.exceptions.*;
import com.ironhack.contactservice.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ContactTest {


    @Autowired
    private ContactRepository contactRepository;

    private Contact contact1;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength, EmailNotValidException, PhoneNumberContainsLettersException {
        contact1 = new Contact("TestOne", "123546", "test1@test.gmail.com", "TestCompany1", 1L);
        contactRepository.save(contact1);
    }

    @Test
    void setName_shouldWork() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
        contact1.setName("Carlos Vasquéz");
        assertEquals("Carlos Vasquéz", contact1.getName());
    }

    @Test
    void setName_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { contact1.setName("");});
    }

    @Test
    void setName_throwsException_ContainsNumbers() {
        assertThrows(NameContainsNumbersException.class, () -> { contact1.setName("Lee1");});
    }

    @Test
    void setName_throwsException_ExceedsMaxCharacters() {
        assertThrows(ExceedsMaxLength.class, () -> { contact1.setName("Arbitrary Name" +
                "to test if the name exceeds the max length of forty three characters");});
    }

    @Test
    void setPhoneNumber_shouldWork() throws EmptyStringException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        contact1.setPhoneNumber("123456");
        assertEquals("123456", contact1.getPhoneNumber());

    }

    @Test
    void setPhoneNumber_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { contact1.setPhoneNumber("");});
    }

    @Test
    void setPhoneNumber_throwsException_ContainsNumbers() {
        assertThrows(PhoneNumberContainsLettersException.class, () -> { contact1.setPhoneNumber("123a56");});
    }

    @Test
    void setPhoneNumber_throwsException_ExceedsMaxCharacters() {
        assertThrows(ExceedsMaxLength.class, () -> { contact1.setPhoneNumber("12345678901234567890123");});
    }

    @Test
    void setEmail_shouldWork() throws EmptyStringException, EmailNotValidException, ExceedsMaxLength {
        contact1.setEmail("contact1@mail.com");
        assertEquals("contact1@mail.com", contact1.getEmail());
    }

    @Test
    void setEmail_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { contact1.setEmail("");});
    }

    @Test
    void setEmail_throwsException_InvalidEmail() {

        //Wrong Format - Missing @
        assertThrows(EmailNotValidException.class, () -> { contact1.setEmail("contact1mail.com");});

        //Wrong Format - Missing .
        assertThrows(EmailNotValidException.class, () -> { contact1.setEmail("contact1@mailcom");});

        // Invalid TLD
        assertThrows(EmailNotValidException.class, () -> { contact1.setEmail("contact1@mail.crom");});
    }

    @Test
    void setEmail_throwsException_ExceedsMaxCharacters() {
        assertThrows(ExceedsMaxLength.class, () -> { contact1.setEmail("lead1withalotofcharactersfortesting@mail.com");});
    }

    @Test
    void setCompanyName_shouldWork() throws EmptyStringException, ExceedsMaxLength {
        contact1.setCompanyName("Company of Contact 1");
        assertEquals("Company of Contact 1", contact1.getCompanyName());
    }


    @Test
    void setCompanyName_throwsException_EmptyString() {
        assertThrows(EmptyStringException.class, () -> { contact1.setCompanyName("");});
    }

    @Test
    void setCompanyName_throwsException_ExceedsMaxCharacters() {
        assertThrows(ExceedsMaxLength.class, () -> { contact1.setCompanyName("This is an extra long company" +
                "name with some extra characters");});
    }
}
