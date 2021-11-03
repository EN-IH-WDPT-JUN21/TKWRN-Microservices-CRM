package com.ironhack.stolen_name_trucking_company_homework_3.menus;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.*;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndustryReportMenuTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IndustryReportMenu test;

    List<SalesRepRequestDTO> salesRepRequestDTOS;
    List<LeadRequestDTO> leadRequestDTOS;
    List<ContactRequestDTO> contactRequestDTOS;
    List<OpportunityRequestDTO> opportunities;
    List<AccountRequestDTO> accountRequestDTOS;
    String expectedOutput;

    @BeforeEach
    public void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException, InvalidCountryException {
        salesRepRequestDTOS = salesRepRepository.saveAll(List.of(
                new SalesRepRequestDTO("David Brown"),
                new SalesRepRequestDTO("Marta Stewart")
        ));

        leadRequestDTOS = leadRepository.saveAll(List.of(
                new LeadRequestDTO("Roger Federer", "123456789", "feds@gmail.co", "Wings of Freedom", salesRepRequestDTOS.get(0)),
                new LeadRequestDTO("Axel Rose", "980651164", "ax@gmail.com", "Roses", salesRepRequestDTOS.get(0)),
                new LeadRequestDTO("Lionel Messi", "563782789", "messi@yahoo.com", "Still Not Retired", salesRepRequestDTOS.get(1))
        ));

        contactRequestDTOS = contactRepository.saveAll(List.of(
                new ContactRequestDTO("John Dowe", "123475357", "alfa@beta.uk", "Kałasznikow", salesRepRequestDTOS.get(0)),
                new ContactRequestDTO("Marta Steward", "123475357", "ms@wp.pl", "My own company", salesRepRequestDTOS.get(1)),
                new ContactRequestDTO("George Trumane", "123475357", "thisisverylongemail@gmail.com", "Truman Show", salesRepRequestDTOS.get(0))

        ));

        opportunities = opportunityRepository.saveAll(List.of(
                new OpportunityRequestDTO(Truck.FLATBED, 10, contactRequestDTOS.get(0), salesRepRequestDTOS.get(0)),
                new OpportunityRequestDTO(Truck.BOX, 1150, contactRequestDTOS.get(1), salesRepRequestDTOS.get(0)),
                new OpportunityRequestDTO(Truck.HYBRID, 1, contactRequestDTOS.get(2), salesRepRequestDTOS.get(1))

        ));

        accountRequestDTOS = accountRepository.saveAll(List.of(
                new AccountRequestDTO(Industry.PRODUCE, 50, "London", "UNITED KINGDOM", contactRequestDTOS.get(0), opportunities.get(0)),
                new AccountRequestDTO(Industry.ECOMMERCE, 500, "Madrid", "SPAIN", contactRequestDTOS.get(1), opportunities.get(1)),
                new AccountRequestDTO(Industry.MANUFACTURING, 20, "Paris", "FRANCE", contactRequestDTOS.get(2), opportunities.get(2))
        ));

        contactRequestDTOS.get(0).setAccount(accountRequestDTOS.get(0));
        contactRepository.save(contactRequestDTOS.get(0));
        contactRequestDTOS.get(1).setAccount(accountRequestDTOS.get(1));
        contactRepository.save(contactRequestDTOS.get(1));
        contactRequestDTOS.get(2).setAccount(accountRequestDTOS.get(2));
        contactRepository.save(contactRequestDTOS.get(2));

        opportunities.get(0).setAccountRequestDTO(accountRequestDTOS.get(0));
        opportunityRepository.save(opportunities.get(0));
        opportunities.get(1).setAccountRequestDTO(accountRequestDTOS.get(1));
        opportunityRepository.save(opportunities.get(1));
        opportunities.get(2).setAccountRequestDTO(accountRequestDTOS.get(2));
        opportunityRepository.save(opportunities.get(2));
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void industryReportMenu_ReportOppByInd() throws NoSuchValueException, AWTException {
        String data = "report opportunity by industry \n\n";
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            test.industryReportMenu();
            assertTrue(outContent.toString().contains("PRODUCE"));
            assertTrue(outContent.toString().contains("Report"));

        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void industryReportMenu_ReportOppByIndOpen() throws NoSuchValueException, AWTException {
        String data = "report open by industry \n\n";
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            test.industryReportMenu();
            assertTrue(outContent.toString().contains("PRODUCE"));
            assertTrue(outContent.toString().contains("ECOMMERCE"));
            assertTrue(outContent.toString().contains("MANUFACTURING"));
            assertTrue(outContent.toString().contains("Report"));

        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void industryReportMenu_ReportOppByIndClsedWon() throws NoSuchValueException, AWTException {
        opportunities.get(0).setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunities.get(0));
        String data = "report closed-won by industry \n\n";
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            test.industryReportMenu();
            assertTrue(outContent.toString().contains("PRODUCE"));
            assertTrue(outContent.toString().contains("Report"));

        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void industryReportMenu_ReportOppByIndClsedLost() throws NoSuchValueException, AWTException {
        opportunities.get(1).setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunities.get(1));
        String data = "report closed-lost by industry \n\n";
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            test.industryReportMenu();
            assertTrue(outContent.toString().contains("ECOMMERCE"));
            assertTrue(outContent.toString().contains("Report"));

        } finally {
            System.setIn(stdin);
        }
    }
}