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
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
class MainMenuTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application application;

    @Autowired
    private MainMenu test;

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

    String colorMain = "\033[0;33m";
    String colorMainBold = "\033[1;37m";
    String colorHeadline = "\033[0;34m";
    String colorHeadlineBold = "\033[1;34m";
    String colorTable = "\033[1;32m";
    String reset = "\u001B[0m";
    String os = System.getProperty("os.name").toLowerCase();
    String expectedOutput;
    List<SalesRepRequestDTO> salesRepRequestDTOS;
    List<LeadRequestDTO> leadRequestDTOS;
    List<ContactRequestDTO> contactRequestDTOS;
    List<OpportunityRequestDTO> opportunities;
    List<AccountRequestDTO> accountRequestDTOS;


    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength, InvalidCountryException, IdContainsLettersException {

        salesRepRequestDTOS = salesRepRepository.saveAll(List.of(
                new SalesRepRequestDTO("David Lynch"),
                new SalesRepRequestDTO("Martha Stewart")
        ));

       leadRequestDTOS = leadRepository.saveAll(List.of(
                new LeadRequestDTO("Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", salesRepRequestDTOS.get(0)),
                new LeadRequestDTO("Lee Dawson", "980651164", "ld@gmail.com", "LeeD", salesRepRequestDTOS.get(0)),
                new LeadRequestDTO("Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland", salesRepRequestDTOS.get(1))
        ));

        contactRequestDTOS = contactRepository.saveAll(List.of(
                new ContactRequestDTO("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", salesRepRequestDTOS.get(0)),
                new ContactRequestDTO("Martha Steward", "123475357", "ms@wp.pl", "My own company", salesRepRequestDTOS.get(1)),
                new ContactRequestDTO("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show", salesRepRequestDTOS.get(0))

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
    void testNewLeadPositive() {
        String data = "y \n Nathan \n 0028263 \n 122@gmail.com \n Santander \n " + salesRepRequestDTOS.get(0).getId() + "\n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test
            LeadRequestDTO theNewLeadRequestDTO = test.newLead(); // creates new lead
            //check Object created correctly and added to repository
            assertEquals("NATHAN", theNewLeadRequestDTO.getName());
            assertEquals("SANTANDER", theNewLeadRequestDTO.getCompanyName());
            assertEquals("0028263", theNewLeadRequestDTO.getPhoneNumber());
            assertEquals("122@GMAIL.COM", theNewLeadRequestDTO.getEmail());
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }

    @Test
    void testConvertLeadThrowsNullPointerException() {
        assertThrows(NoSuchElementException.class, () -> test.convertLead("239832487248"));
        assertThrows(NumberFormatException.class, () -> test.convertLead("Sausage"));
    }


    @Test
    void testConvertLeadPositive() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {

        String data = "y \n box \n 20 \n \n \n y \n \n \n \n";
        InputStream stdin = System.in; // Used to store default System.in

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1

            var oppRepoSizeBefore = opportunityRepository.findAllOpportunities().size();

            OpportunityRequestDTO newOpp = test.convertLead(leadRequestDTOS.get(0).getId().toString());

            var oppRepoSizeAfter = opportunityRepository.findAllOpportunities().size();
            assertEquals(Truck.BOX, newOpp.getProduct());
            assertEquals(20, newOpp.getQuantity());
            assertEquals(oppRepoSizeAfter, oppRepoSizeBefore + 1);

        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }


    @Test
    void createAccount_PositiveTest() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        String data = "y \n Produce\n 200 \n Stourbridge \n SPAIN\n \n \n \n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1

            AccountRequestDTO createdAccountRequestDTO = test.createAccount(opportunities.get(0));

            assertEquals("Kałasznikow", createdAccountRequestDTO.getCompanyName());
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }

    @Test
    void createAccount_AddToExistingAccountTest() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        String data = "n \n" + accountRequestDTOS.get(0).getId() +"\n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in
        SalesRepRequestDTO testRep = new SalesRepRequestDTO("Test Rep");
        salesRepRepository.save(testRep);
        ContactRequestDTO testContactRequestDTO = new ContactRequestDTO("Test Contact", "98765432", "test@contact.co.uk", "Test Company", testRep);
        contactRepository.save(testContactRequestDTO);
        OpportunityRequestDTO testOpp = new OpportunityRequestDTO(Truck.HYBRID, 50, contactRepository.findById(testContactRequestDTO.getId()).get(),
                salesRepRepository.findById(testRep.getId()).get());
        opportunityRepository.save(testOpp);

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1
            var oppSizeBefore = accountRepository.findById(accountRequestDTOS.get(0).getId()).get().getOpportunityList().size();

            AccountRequestDTO existingAccountRequestDTO = test.createAccount(testOpp);

            var oppSizeAfter = accountRepository.findById(accountRequestDTOS.get(0).getId()).get().getOpportunityList().size();

            assertEquals(existingAccountRequestDTO, testOpp.getAccountRequestDTO());
            assertEquals(oppSizeAfter, oppSizeBefore + 1);
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }



    @Test
    void showSalesReps(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showSalesReps();

        assertTrue(outContent.toString().contains("DAVID LYNCH"));
        assertTrue(outContent.toString().contains("Total Number Of Sales Representatives:"));
    }


    @Test
    void showLeads() {

        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showLeads();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        assertTrue(outContent.toString().contains("LEE DAWSON"));
        assertTrue(outContent.toString().contains("Total Number Of Leads"));
    }


    @Test
    void showContacts() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showContacts();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        assertTrue(outContent.toString().contains("JOHN DOE"));
        assertTrue(outContent.toString().contains("Total Number Of Contacts"));
    }

    //
    @Test
    void showOpportunities() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();
        accountRepository.deleteAll();

        ContactRequestDTO contactRequestDTO = new ContactRequestDTO("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
        contactRepository.save(contactRequestDTO);
        OpportunityRequestDTO newOpp = new OpportunityRequestDTO(Truck.HYBRID, 30, contactRequestDTO);
        opportunityRepository.save(newOpp);

        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        test.showOpportunities();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        assertTrue(outContent.toString().contains("HYBRID"));
        assertTrue(outContent.toString().contains("Total Number Of Opportunities"));
    }

    @Test
    void showAccounts() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        // Clears the repositories for easier testing
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();

        // This Block looks messy but sets up the Repositories for testing
        ContactRequestDTO testContactRequestDTO = new ContactRequestDTO("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
        contactRepository.save(testContactRequestDTO);
        OpportunityRequestDTO testOpp = new OpportunityRequestDTO(Truck.HYBRID, 30, testContactRequestDTO);
        opportunityRepository.save(testOpp);
        AccountRequestDTO testAcc = new AccountRequestDTO(testContactRequestDTO, testOpp);
        accountRepository.save(testAcc);
        testOpp.getDecisionMaker().setAccount(testAcc);
        contactRepository.save(testOpp.getDecisionMaker());
        accountRepository.save(testAcc);


        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showAccounts();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        assertTrue(outContent.toString().contains("TESTCOMPANY"));
        assertTrue(outContent.toString().contains("Total Number Of Accounts"));
    }

    @Test
    void lookUpLeadId_FindLead() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        SalesRepRequestDTO testRep = new SalesRepRequestDTO("Test Rep");
        salesRepRepository.save(testRep);
        LeadRequestDTO testLeadRequestDTO = new LeadRequestDTO("Test Lead","123456789", "test@testlead.com", "Test Company", testRep);
        leadRepository.save(testLeadRequestDTO);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.lookUpLeadId(testLeadRequestDTO.getId());

        assertTrue(outContent.toString().contains("TEST LEAD"));
        assertTrue(outContent.toString().contains("Lead details"));
    }

    @Test
    void lookUpOppId_FindOpp() throws ExceedsMaxLength {
        ContactRequestDTO testContactRequestDTO = new ContactRequestDTO("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
        contactRepository.save(testContactRequestDTO);
        OpportunityRequestDTO testOpp = new OpportunityRequestDTO(Truck.HYBRID, 30, testContactRequestDTO);
        opportunityRepository.save(testOpp);
        AccountRequestDTO testAcc = new AccountRequestDTO(testContactRequestDTO, testOpp);
        accountRepository.save(testAcc);
        testOpp.getDecisionMaker().setAccount(testAcc);
        contactRepository.save(testOpp.getDecisionMaker());
        accountRepository.save(testAcc);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.lookUpOppId(String.valueOf(testOpp.getId()));

        assertTrue(outContent.toString().contains("HYBRID"));
        assertTrue(outContent.toString().contains("Contract details"));
        assertTrue(outContent.toString().contains("Decision maker details"));
    }


    @Test
    void closeLost() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {

        String data = "y \n";
        InputStream stdin = System.in;

        try {
            assertEquals(Status.OPEN, opportunityRepository.findById(opportunities.get(0).getId()).get().getStatus());
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            test.closeLost(opportunities.get(0).getId().toString());
            assertEquals(Status.CLOSED_LOST, opportunityRepository.findById(opportunities.get(0).getId()).get().getStatus());
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void closeWon() throws ExceedsMaxLength, NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException {
        String data = "y";
        InputStream stdin = System.in;
        try {
            assertEquals(Status.OPEN, opportunityRepository.findById(opportunities.get(1).getId()).get().getStatus());
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            test.closeWon(opportunities.get(1).getId().toString());
            assertEquals(Status.CLOSED_WON, opportunityRepository.findById(opportunities.get(1).getId()).get().getStatus());

        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void TestCreateSalesrepPositive() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        String data = "y\n Diego Maradona \n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1

            SalesRepRequestDTO createdSalesrep = test.newSalesRep();

            assertEquals("DIEGO MARADONA", createdSalesrep.getRepName());
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }

}
