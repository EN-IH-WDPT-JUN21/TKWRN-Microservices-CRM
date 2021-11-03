package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.AccountRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.ContactRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.OpportunityRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.SalesRepRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRequestDTORepositoryTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AccountRepository accountRepository;

    private List<SalesRepRequestDTO> salesRepRequestDTOS;
    private List<ContactRequestDTO> contactRequestDTOS;
    private List<OpportunityRequestDTO> opportunities;
    private List<AccountRequestDTO> accountRequestDTOS;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength,
            PhoneNumberContainsLettersException, InvalidCountryException {

        salesRepRequestDTOS = salesRepRepository.saveAll(List.of(
                new SalesRepRequestDTO("David Lynch"),
                new SalesRepRequestDTO("Martha Stewart")
        ));

        contactRequestDTOS = contactRepository.saveAll(List.of(
                new ContactRequestDTO("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow",
                        salesRepRequestDTOS.get(0)),
                new ContactRequestDTO("Martha Steward", "123475357", "ms@wp.pl",
                        "My own company", salesRepRequestDTOS.get(1)),
                new ContactRequestDTO("George Truman", "123475357", "thisisverylongemail@gmail.com",
                        "Truman Show", salesRepRequestDTOS.get(0))

        ));

        opportunities = opportunityRepository.saveAll(List.of(
                new OpportunityRequestDTO(Truck.FLATBED, 10, contactRequestDTOS.get(0), salesRepRequestDTOS.get(0)),
                new OpportunityRequestDTO(Truck.BOX, 1150, contactRequestDTOS.get(1), salesRepRequestDTOS.get(0)),
                new OpportunityRequestDTO(Truck.HYBRID, 1, contactRequestDTOS.get(2), salesRepRequestDTOS.get(1))

        ));

        accountRequestDTOS = accountRepository.saveAll(List.of(
                new AccountRequestDTO(Industry.PRODUCE, 50, "London", "UNITED KINGDOM", contactRequestDTOS.get(0),
                        opportunities.get(0))  ,
                new AccountRequestDTO(Industry.ECOMMERCE, 500, "Madrid", "SPAIN", contactRequestDTOS.get(1),
                        opportunities.get(1)),
                new AccountRequestDTO(Industry.MANUFACTURING, 20, "Paris", "FRANCE", contactRequestDTOS.get(2),
                        opportunities.get(2))
        ));

    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();
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


    @Test
    void findAllAccounts() {
    }
}