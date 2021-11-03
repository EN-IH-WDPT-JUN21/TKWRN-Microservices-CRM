//package com.ironhack.menuservice.dao;
//
//import com.ironhack.menuservice.dto.LeadRequestDTO;
//import com.ironhack.menuservice.exceptions.*;
//import org.junit.Assert;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class LeadRequestDTOTest {
//
//    private LeadRequestDTO leadRequestDTO1;
//
//
//    @BeforeEach
//    void setUp() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength, EmailNotValidException, PhoneNumberContainsLettersException {
//        leadRequestDTO1 = new LeadRequestDTO("TestOne", "123546", "test1@test.gmail.com", "TestCompany1");
//
//
//    }
//
//    @AfterEach
//    void tearDown() {
//
//    }
//
//    @Test
//    void setName_shouldWork() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
//        leadRequestDTO1.setName("Carlos Vasquéz");
//        assertEquals("Carlos Vasquéz", leadRequestDTO1.getName());
//
//    }
//
//    @Test
//    void setName_throwsException_EmptyString() {
//        Assert.assertThrows(EmptyStringException.class, () -> { leadRequestDTO1.setName("");});
//    }
//
//    @Test
//    void setName_throwsException_ContainsNumbers() {
//        Assert.assertThrows(NameContainsNumbersException.class, () -> { leadRequestDTO1.setName("Lee1");});
//    }
//
//    @Test
//    void setName_throwsException_ExceedsMaxCharacters() {
//        Assert.assertThrows(ExceedsMaxLength.class, () -> { leadRequestDTO1.setName("Arbitrary Name" +
//                "to test if the name exceeds the max length of forty three characters");});
//    }
//
//
//    @Test
//    void setPhoneNumber_shouldWork() throws EmptyStringException, ExceedsMaxLength, PhoneNumberContainsLettersException {
//        leadRequestDTO1.setPhoneNumber("123456");
//        assertEquals("123456", leadRequestDTO1.getPhoneNumber());
//
//    }
//
//    @Test
//    void setPhoneNumber_throwsException_EmptyString() {
//        Assert.assertThrows(EmptyStringException.class, () -> { leadRequestDTO1.setPhoneNumber("");});
//    }
//
//    @Test
//    void setPhoneNumber_throwsException_ContainsNumbers() {
//        Assert.assertThrows(PhoneNumberContainsLettersException.class, () -> { leadRequestDTO1.setPhoneNumber("123a56");});
//    }
//
//    @Test
//    void setPhoneNumber_throwsException_ExceedsMaxCharacters() {
//        Assert.assertThrows(ExceedsMaxLength.class, () -> { leadRequestDTO1.setPhoneNumber("12345678901234567890123");});
//    }
//
//    @Test
//    void setEmail_shouldWork() throws EmptyStringException, EmailNotValidException, ExceedsMaxLength {
//        leadRequestDTO1.setEmail("lead2@mail.com");
//        assertEquals("lead2@mail.com", leadRequestDTO1.getEmail());
//    }
//
//    @Test
//    void setEmail_throwsException_EmptyString() {
//        Assert.assertThrows(EmptyStringException.class, () -> { leadRequestDTO1.setEmail("");});
//    }
//
//    @Test
//    void setEmail_throwsException_InvalidEmail() {
//
//        //Wrong Format - Missing @
//        Assert.assertThrows(EmailNotValidException.class, () -> { leadRequestDTO1.setEmail("lead1mail.com");});
//
//        //Wrong Format - Missing .
//        Assert.assertThrows(EmailNotValidException.class, () -> { leadRequestDTO1.setEmail("lead1@mailcom");});
//
//        // Invalid TLD
//        Assert.assertThrows(EmailNotValidException.class, () -> { leadRequestDTO1.setEmail("lead1@mail.crom");});
//    }
//
//     @Test
//    void setEmail_throwsException_ExceedsMaxCharacters() {
//         Assert.assertThrows(ExceedsMaxLength.class, () -> { leadRequestDTO1.setEmail("lead1withalotofcharactersfortesting@mail.com");});
//     }
//
//    @Test
//    void setCompanyName_shouldWork() throws EmptyStringException, ExceedsMaxLength {
//        leadRequestDTO1.setCompanyName("Company of Lead 1");
//        assertEquals("Company of Lead 1", leadRequestDTO1.getCompanyName());
//    }
//
//
//    @Test
//    void setCompanyName_throwsException_EmptyString() {
//        Assert.assertThrows(EmptyStringException.class, () -> { leadRequestDTO1.setCompanyName("");});
//    }
//
//    @Test
//    void setCompanyName_throwsException_ExceedsMaxCharacters() {
//        Assert.assertThrows(ExceedsMaxLength.class, () -> { leadRequestDTO1.setCompanyName("This is an extra long company" +
//                "name with some extra characters");});
//    }
//
//}