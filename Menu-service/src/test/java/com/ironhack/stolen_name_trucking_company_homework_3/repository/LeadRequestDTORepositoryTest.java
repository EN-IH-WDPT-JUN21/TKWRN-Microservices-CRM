package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.LeadRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.SalesRepRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class LeadRequestDTORepositoryTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private LeadRepository leadRepository;


    private List<SalesRepRequestDTO> salesRepRequestDTOS;
    private List<LeadRequestDTO> leadRequestDTOS;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException, IdContainsLettersException {

        salesRepRequestDTOS = salesRepRepository.saveAll(List.of(
                new SalesRepRequestDTO("David Lynch"),
                new SalesRepRequestDTO("Martha Stewart")
                ));

        leadRequestDTOS = leadRepository.saveAll(List.of(
                new LeadRequestDTO("Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", salesRepRequestDTOS.get(0)),
                new LeadRequestDTO("Lee Dawson", "980651164", "ld@gmail.com", "LeeD", salesRepRequestDTOS.get(0)),
                new LeadRequestDTO("Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland", salesRepRequestDTOS.get(1))

        ));


    }

    @AfterEach
    void tearDown() {
       leadRepository.deleteAll();
       salesRepRepository.deleteAll();
    }


    @Test
    public void createLeads_positiveCreation(){
        var leadsy = leadRepository.findAll();
        var salesRepsy = salesRepRepository.findAll();
        assertEquals(3, leadsy.size());
        assertEquals(2, salesRepsy.size());
        //assertEquals("David Lynch", leadRepository.findAll().get(3));
    }


    @Test
    void getCountOfLeadsGroupBySalesRep_Test() {
        var leadByRep = leadRepository.findCountLeadByRepName();

        assertEquals("David Lynch", leadByRep.get(0)[0]);
        assertEquals("Martha Stewart", leadByRep.get(1)[0]);
        assertEquals(1L, leadByRep.get(1)[1]);
        assertEquals(2L,leadByRep.get(0)[1]);
    }


}