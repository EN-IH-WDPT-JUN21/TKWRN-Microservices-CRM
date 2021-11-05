package com.ironhack.salesrepservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.LeadDTO;
import com.ironhack.salesrepservice.dto.OpportunityDTO;
import com.ironhack.salesrepservice.dto.SalesRepDTO;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SalesRepControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SalesRepRepository salesRepRepository;

    private SalesRep salesRep1;
    private SalesRepDTO salesRepDTO1;
    private SalesRep salesRep2;
    private SalesRepDTO salesRepDTO2;
    private LeadDTO leadDTO1;
    private LeadDTO leadDTO2;
    private List<LeadDTO> leadDTOList;
    private OpportunityDTO opportunityDTO1;
    private OpportunityDTO opportunityDTO2;
    private List<OpportunityDTO> opportunityDTOList;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        salesRep1 = new SalesRep("Tom Segura");
        salesRepDTO1 = new SalesRepDTO(1L, "Tom Segura");
        salesRep2 = new SalesRep("Joe Rogan");
        salesRepDTO2 = new SalesRepDTO(2L, "Joe Rogan");
        salesRepRepository.saveAll(List.of(salesRep1,salesRep2));

        /*leadDTO1 = new LeadDTO(1L, 1L);
        leadDTO2 = new LeadDTO(2L, 2L);
        leadDTOList.add(leadDTO1);
        leadDTOList.add(leadDTO2);

        opportunityDTO1 = new OpportunityDTO(1L, 2L);
        opportunityDTO2 = new OpportunityDTO(2L, 1L);
        opportunityDTOList.add(opportunityDTO1);
        opportunityDTOList.add(opportunityDTO2);*/

    }

    @AfterEach
    void tearDown() {
        salesRepRepository.delete(salesRep1);
        salesRepRepository.delete(salesRep2);
    }

    @Test
    void addSalesRep() throws Exception {
        SalesRep salesRep = new SalesRep("Michael Jordan");
        String body = objectMapper.writeValueAsString(salesRep);
        MvcResult result = mockMvc.perform(
                post("/api/v1/sales-reps/new")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Jordan"));
        assertFalse(result.getResponse().getContentAsString().contains("Pippen"));
    }

    @Test
    void getAllSalesReps()  throws Exception {
        MvcResult result = mockMvc.perform(
                get("/api/v1/sales-reps")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Segura"));
        assertFalse(result.getResponse().getContentAsString().contains("Carlin"));
    }

    @Test
    void getSalesRepById_ValidId_SalesRep() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/api/v1/sales-reps/" + salesRep1.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Segura"));
        assertFalse(result.getResponse().getContentAsString().contains("Rogan"));
    }

    @Test
    void updateSalesRepName() {
    }

    @Test
    void deleteSalesRep() throws Exception {
        var sizeBefore = salesRepRepository.findAll().size();
        MvcResult result = mockMvc.perform(
                delete("/api/v1/sales-reps/delete/" + salesRep1.getId())
        ).andExpect(status().isOk()).andReturn();
        var sizeAfter = salesRepRepository.findAll().size();

        assertEquals(1, sizeBefore - sizeAfter);
        assertNotEquals(2, sizeBefore - sizeAfter);
    }

    @Test
    void createSalesrepDatabase() {
    }
}