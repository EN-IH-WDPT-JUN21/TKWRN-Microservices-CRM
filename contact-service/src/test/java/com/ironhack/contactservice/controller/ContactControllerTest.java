package com.ironhack.contactservice.controller;

import com.ironhack.contactservice.dao.Contact;
import com.ironhack.contactservice.dto.ContactDTO;
import com.ironhack.contactservice.repository.ContactRepository;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ContactControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ContactRepository contactRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        List<Contact> contacts = contactRepository.saveAll(
                List.of(
                        new Contact("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", 1L),
                        new Contact("Martha Steward", "123475357", "ms@wp.pl", "My own company", 2L),
                        new Contact("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show", 2L)));
    }

    @Test
    void findAllContacts() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/contacts")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("John Doe"));
        assertTrue(result.getResponse().getContentAsString().contains("Martha Steward"));
        assertTrue(result.getResponse().getContentAsString().contains("George Truman"));
    }

    @Test
    void findByContactId_ContactExists() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/contacts/1")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("John Doe"));
    }

    @Test
    void findByContactId_ContactNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/1444")).andExpect(status().isNotFound()).andReturn();
    }


    @Test
    void createNewContact() throws Exception {
        ContactDTO contactDTO;
        contactDTO = new ContactDTO();
        contactDTO.setId(4L);
        contactDTO.setName("Mary Jane");
        contactDTO.setEmail("MaryJ@gmail.com");
        contactDTO.setPhoneNumber("12345678");
        contactDTO.setCompanyName("Webs");
        contactDTO.setSalesId(1L);
        String body = objectMapper.writeValueAsString(contactDTO);
        MvcResult result = mockMvc.perform(post("/api/v1/contacts").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Mary Jane"));
    }
}

