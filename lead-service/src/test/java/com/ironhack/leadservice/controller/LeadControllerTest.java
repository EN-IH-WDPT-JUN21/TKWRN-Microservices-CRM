package com.ironhack.leadservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.repository.LeadRepository;
import com.ironhack.leadservice.service.LeadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class LeadControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadService leadService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        List<Lead> leads = leadRepository.saveAll(List.of(
                new Lead("Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", 1L),
                new Lead("Lee Dawson", "980651164", "ld@gmail.com", "LeeD", 1L),
                new Lead("Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland", 2L)
        ));
    }

    @Test
    void findAllLeads() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/leads")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Sebastian Marek Labedz"));
        assertTrue(result.getResponse().getContentAsString().contains("Lee Dawson"));
        assertTrue(result.getResponse().getContentAsString().contains("Natalia Shilyaeva"));
    }

    @Test
    void findByLeadId_LeadExists() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/leads/3")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Natalia Shilyaeva"));
    }

    @Test
    void findByLeadId_LeadNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/leads/14")).andExpect(status().isNotFound()).andReturn();
    }

//
//    @Test
//    void createNewLead() throws Exception {
//        LeadDTO leadDTO;
//        leadDTO = new LeadDTO();
//        leadDTO.setId(4L);
//        leadDTO.setName("Mary Jane");
//        leadDTO.setEmail("MaryJ@gmail.com");
//        leadDTO.setPhoneNumber("12345678");
//        leadDTO.setCompanyName("Webs");
//        leadDTO.setSalesId(1L);
//        String body = objectMapper.writeValueAsString(leadDTO);
//        MvcResult result = mockMvc.perform(post("/api/v1/leads").content(body)
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
//        assertTrue(result.getResponse().getContentAsString().contains("Mary Jane"));
//    }

    @Test
    void convertLead() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/leads/1/convert?product=HYBRID&quantity=5")).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("HYBRID"));
    }


}
