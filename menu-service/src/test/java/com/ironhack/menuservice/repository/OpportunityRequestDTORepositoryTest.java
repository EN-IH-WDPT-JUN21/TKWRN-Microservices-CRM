//package com.ironhack.menuservice.repository;
//
//
//import com.ironhack.menuservice.StolenNameTruckingCompanyHomework3Application;
//import com.ironhack.menuservice.dto.AccountRequestDTO;
//import com.ironhack.menuservice.dto.ContactRequestDTO;
//import com.ironhack.menuservice.dto.OpportunityRequestDTO;
//import com.ironhack.menuservice.dto.SalesRepRequestDTO;
//import com.ironhack.menuservice.enums.Industry;
//import com.ironhack.menuservice.enums.Status;
//import com.ironhack.menuservice.enums.Truck;
//import com.ironhack.menuservice.exceptions.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class OpportunityRequestDTORepositoryTest {
//
//    @MockBean
//    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;
//
//    @Autowired
//    private OpportunityRepository opportunityRepository;
//
//    @Autowired
//    private SalesRepRepository salesRepRepository;
//
//    @Autowired
//    private ContactRepository contactRepository;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    private List<SalesRepRequestDTO> salesRepRequestDTOS;
//    private List<ContactRequestDTO> contactRequestDTOS;
//    private List<OpportunityRequestDTO> opportunities;
//    private List<AccountRequestDTO> accountRequestDTOS;
//
//    @BeforeEach
//    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException, InvalidCountryException {
//
//        salesRepRequestDTOS = salesRepRepository.saveAll(List.of(
//                new SalesRepRequestDTO("David Lynch"),
//                new SalesRepRequestDTO("Martha Stewart")
//        ));
//
//        contactRequestDTOS = contactRepository.saveAll(List.of(
//                new ContactRequestDTO("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", salesRepRequestDTOS.get(0)),
//                new ContactRequestDTO("Martha Steward", "123475357", "ms@wp.pl", "My own company", salesRepRequestDTOS.get(1)),
//                new ContactRequestDTO("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show", salesRepRequestDTOS.get(0))
//
//        ));
//
//        opportunities = opportunityRepository.saveAll(List.of(
//                new OpportunityRequestDTO(Truck.FLATBED, 10, contactRequestDTOS.get(0), salesRepRequestDTOS.get(0)),
//                new OpportunityRequestDTO(Truck.BOX, 1150, contactRequestDTOS.get(1), salesRepRequestDTOS.get(0)),
//                new OpportunityRequestDTO(Truck.HYBRID, 1, contactRequestDTOS.get(2), salesRepRequestDTOS.get(1))
//
//        ));
//
//        accountRequestDTOS = accountRepository.saveAll(List.of(
//                new AccountRequestDTO(Industry.PRODUCE, 50, "London", "UNITED KINGDOM", contactRequestDTOS.get(0), opportunities.get(0))  ,
//                new AccountRequestDTO(Industry.ECOMMERCE, 500, "Madrid", "SPAIN", contactRequestDTOS.get(1), opportunities.get(1)),
//                new AccountRequestDTO(Industry.MANUFACTURING, 20, "Paris", "FRANCE", contactRequestDTOS.get(2), opportunities.get(2))
//        ));
//
//        contactRequestDTOS.get(0).setAccount(accountRequestDTOS.get(0));
//        contactRepository.save(contactRequestDTOS.get(0));
//        contactRequestDTOS.get(1).setAccount(accountRequestDTOS.get(1));
//        contactRepository.save(contactRequestDTOS.get(1));
//        contactRequestDTOS.get(2).setAccount(accountRequestDTOS.get(2));
//        contactRepository.save(contactRequestDTOS.get(2));
//
//
//        opportunities.get(0).setAccountRequestDTO(accountRequestDTOS.get(0));
//        opportunityRepository.save(opportunities.get(0));
//        opportunities.get(1).setAccountRequestDTO(accountRequestDTOS.get(1));
//        opportunityRepository.save(opportunities.get(1));
//        opportunities.get(2).setAccountRequestDTO(accountRequestDTOS.get(2));
//        opportunityRepository.save(opportunities.get(2));
//
//
//    }
//
//    @AfterEach
//    void tearDown() {
//        opportunityRepository.deleteAll();
//        contactRepository.deleteAll();
//        salesRepRepository.deleteAll();
//        accountRepository.deleteAll();
//    }
//
//    @Test
//    void findOpportunityById_Test(){
//        var opportunity = opportunityRepository.findById(opportunities.get(1).getId());
//          assertEquals(1150, opportunity.get().getQuantity());
//    }
//
//    @Test
//    void findAllOpportunities_Test(){
//        var opportunityCount = opportunityRepository.findAllOpportunities().size();
//        assertEquals(3, opportunityCount);
//    }
//
//    @Test
//    void findCountOpportunityByRepName_Test() {
//        var leadByRep = opportunityRepository.findCountOpportunityByRepName();
//
//        assertEquals("David Lynch", leadByRep.get(0)[0]);
//        assertEquals("Martha Stewart", leadByRep.get(1)[0]);
//        assertEquals(1L, leadByRep.get(1)[1]);
//        assertEquals(2L,leadByRep.get(0)[1]);
//    }
//
//    @Test
//    void findCountOppForProduct_Test(){
//        var oppByProd = opportunityRepository.findCountOppForProduct();
//        assertEquals(Truck.BOX, oppByProd.get(0)[0]);
//        assertEquals(1L, oppByProd.get(0)[1]);
//        assertEquals(Truck.FLATBED, oppByProd.get(1)[0]);
//        assertEquals(1L, oppByProd.get(1)[1]);
//        assertEquals(Truck.HYBRID, oppByProd.get(2)[0]);
//        assertEquals(1L, oppByProd.get(2)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByRepNameForStatus_TestOpen(){
//        var oppByRepOpen = opportunityRepository.findCountOpportunityByRepNameForStatus(Status.OPEN.toString());
//        assertEquals("David Lynch", oppByRepOpen.get(0)[0]);
//        assertEquals("Martha Stewart", oppByRepOpen.get(1)[0]);
//        assertEquals(1L, oppByRepOpen.get(1)[1]);
//        assertEquals(2L,oppByRepOpen.get(0)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByRepNameForStatus_TestCloseWon(){
//
//        opportunities.get(0).setStatus(Status.CLOSED_WON);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByRepCloseWon = opportunityRepository.findCountOpportunityByRepNameForStatus(Status.CLOSED_WON.toString());
//        assertEquals("David Lynch", oppByRepCloseWon.get(0)[0]);
//        assertEquals(1L,oppByRepCloseWon.get(0)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByRepNameForStatus_TestCloseLost(){
//        opportunities.get(0).setStatus(Status.CLOSED_LOST);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByRepCloseLost = opportunityRepository.findCountOpportunityByRepNameForStatus(Status.CLOSED_LOST.toString());
//        assertEquals("David Lynch", oppByRepCloseLost.get(0)[0]);
//        assertEquals(1L,oppByRepCloseLost.get(0)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByProductForStatus_OPEN(){
//        var oppByProdOpen = opportunityRepository.findCountOpportunityByProductForStatus(Status.OPEN);
//        assertEquals(Truck.BOX, oppByProdOpen.get(0)[0]);
//        assertEquals(1L, oppByProdOpen.get(0)[1]);
//        assertEquals(Truck.FLATBED, oppByProdOpen.get(1)[0]);
//        assertEquals(1L, oppByProdOpen.get(1)[1]);
//        assertEquals(Truck.HYBRID, oppByProdOpen.get(2)[0]);
//        assertEquals(1L, oppByProdOpen.get(2)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByProductForStatus_CLOSED_WON(){
//        opportunities.get(0).setStatus(Status.CLOSED_WON);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByProdCloseWon = opportunityRepository.findCountOpportunityByProductForStatus(Status.CLOSED_WON);
//        assertEquals(Truck.FLATBED, oppByProdCloseWon.get(0)[0]);
//        assertEquals(1L, oppByProdCloseWon.get(0)[1]);
//
//    }
//
//    @Test
//    void findCountOpportunityByProductForStatus_CLOSED_LOST(){
//        opportunities.get(0).setStatus(Status.CLOSED_LOST);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByProdCloseWon = opportunityRepository.findCountOpportunityByProductForStatus(Status.CLOSED_LOST);
//        assertEquals(Truck.FLATBED, oppByProdCloseWon.get(0)[0]);
//        assertEquals(1L, oppByProdCloseWon.get(0)[1]);
//
//    }
//
//    @Test
//    void findCountOppForCountry_Test() {
//        var oppByCountry = opportunityRepository.findCountOppForCountry();
//        assertEquals("FRANCE", oppByCountry.get(0)[0]);
//        assertEquals("SPAIN", oppByCountry.get(1)[0]);
//        assertEquals("UNITED KINGDOM", oppByCountry.get(2)[0]);
//        assertEquals(1L, oppByCountry.get(0)[1]);
//        assertEquals(1L,oppByCountry.get(1)[1]);
//        assertEquals(1L,oppByCountry.get(2)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByCountryForStatus_Open_Test(){
//        var oppByCountryOpen = opportunityRepository.findCountOpportunityByCountryForStatus(Status.OPEN.toString());
//        assertEquals("FRANCE", oppByCountryOpen.get(0)[0]);
//        assertEquals("SPAIN", oppByCountryOpen.get(1)[0]);
//        assertEquals("UNITED KINGDOM", oppByCountryOpen.get(2)[0]);
//        assertEquals(1L, oppByCountryOpen.get(0)[1]);
//        assertEquals(1L,oppByCountryOpen.get(1)[1]);
//        assertEquals(1L,oppByCountryOpen.get(2)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByCountryForStatus_Close_Won_Test(){
//        opportunities.get(0).setStatus(Status.CLOSED_WON);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByCountryCloseWon = opportunityRepository.findCountOpportunityByCountryForStatus(Status.CLOSED_WON.toString());
//        assertEquals("UNITED KINGDOM", oppByCountryCloseWon.get(0)[0]);
//        assertEquals(1L, oppByCountryCloseWon.get(0)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByCountryForStatus_Close_Lost_Test(){
//        opportunities.get(0).setStatus(Status.CLOSED_LOST);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByCountryCloseLost = opportunityRepository.findCountOpportunityByCountryForStatus(Status.CLOSED_LOST.toString());
//        assertEquals("UNITED KINGDOM", oppByCountryCloseLost.get(0)[0]);
//        assertEquals(1L, oppByCountryCloseLost.get(0)[1]);
//
//    }
//
//    @Test
//    void findCountOppForCity_Test() {
//        var oppByCity = opportunityRepository.findCountOppForCity();
//        assertEquals("London", oppByCity.get(0)[0]);
//        assertEquals("Madrid", oppByCity.get(1)[0]);
//        assertEquals("Paris", oppByCity.get(2)[0]);
//        assertEquals(1L, oppByCity.get(0)[1]);
//        assertEquals(1L, oppByCity.get(1)[1]);
//        assertEquals(1L, oppByCity.get(2)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByCityForStatus_Open_Test(){
//        var oppByCityOpen = opportunityRepository.findCountOpportunityByCityForStatus(Status.OPEN.toString());
//        assertEquals("London", oppByCityOpen.get(0)[0]);
//        assertEquals("Madrid", oppByCityOpen.get(1)[0]);
//        assertEquals("Paris", oppByCityOpen.get(2)[0]);
//        assertEquals(1L, oppByCityOpen.get(0)[1]);
//        assertEquals(1L, oppByCityOpen.get(1)[1]);
//        assertEquals(1L, oppByCityOpen.get(2)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByCityForStatus_Close_Won_Test(){
//        opportunities.get(0).setStatus(Status.CLOSED_WON);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByCityCloseWon = opportunityRepository.findCountOpportunityByCityForStatus(Status.CLOSED_WON.toString());
//        assertEquals("London", oppByCityCloseWon.get(0)[0]);
//        assertEquals(1L, oppByCityCloseWon.get(0)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByCityForStatus_Close_Lost_Test(){
//        opportunities.get(0).setStatus(Status.CLOSED_LOST);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByCityCloseLost = opportunityRepository.findCountOpportunityByCityForStatus(Status.CLOSED_LOST.toString());
//        assertEquals("London", oppByCityCloseLost.get(0)[0]);
//        assertEquals(1L, oppByCityCloseLost.get(0)[1]);
//
//    }
//
//    @Test
//    void findCountOppForIndustry_Test() {
//        var oppByIndustry = opportunityRepository.findCountOppForIndustry();
//        assertEquals(Industry.ECOMMERCE, oppByIndustry.get(0)[0]);
//        assertEquals(Industry.MANUFACTURING, oppByIndustry.get(1)[0]);
//        assertEquals(Industry.PRODUCE, oppByIndustry.get(2)[0]);
//        assertEquals(1L, oppByIndustry.get(0)[1]);
//        assertEquals(1L, oppByIndustry.get(1)[1]);
//        assertEquals(1L, oppByIndustry.get(2)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByIndustryForStatus_Open_Test(){
//        var oppByIndustryOpen = opportunityRepository.findCountOpportunityByIndustryForStatus(Status.OPEN.toString());
//        assertEquals(Industry.ECOMMERCE, oppByIndustryOpen.get(0)[0]);
//        assertEquals(Industry.MANUFACTURING, oppByIndustryOpen.get(1)[0]);
//        assertEquals(Industry.PRODUCE, oppByIndustryOpen.get(2)[0]);
//        assertEquals(1L, oppByIndustryOpen.get(0)[1]);
//        assertEquals(1L, oppByIndustryOpen.get(1)[1]);
//        assertEquals(1L, oppByIndustryOpen.get(2)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByIndustryForStatus_Close_Won_Test(){
//        opportunities.get(0).setStatus(Status.CLOSED_WON);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByIndustryCloseWon = opportunityRepository.findCountOpportunityByIndustryForStatus(Status.CLOSED_WON.toString());
//        assertEquals(Industry.PRODUCE, oppByIndustryCloseWon.get(0)[0]);
//        assertEquals(1L, oppByIndustryCloseWon.get(0)[1]);
//    }
//
//    @Test
//    void findCountOpportunityByIndustryForStatus_Close_Lost_Test(){
//        opportunities.get(0).setStatus(Status.CLOSED_LOST);
//        opportunityRepository.save(opportunities.get(0));
//        var oppByIndustryCloseLost = opportunityRepository.findCountOpportunityByIndustryForStatus(Status.CLOSED_LOST.toString());
//        assertEquals(Industry.PRODUCE, oppByIndustryCloseLost.get(0)[0]);
//        assertEquals(1L, oppByIndustryCloseLost.get(0)[1]);
//
//    }
//
//    @Test
//    void findMeanProductQuantity() {
//        var meanProductQuantity = opportunityRepository.findMeanProductQuantity();
//        assertEquals(387, meanProductQuantity.get().doubleValue());
//    }
//
//    @Test
//    void findMedianQuantityStep1_test(){
//        var medianQuantity = opportunityRepository.findMedianQuantityStep1();
//        assertEquals(3, medianQuantity.length);
//    }
//
//    @Test
//    void findMaxProductQuantity() {
//        var maxProductQuantity = opportunityRepository.findMaxProductQuantity();
//        assertEquals(1150, maxProductQuantity.get().intValue());
//    }
//
//
//
//    @Test
//    void findMinProductQuantity() {
//        var minProductQuantity = opportunityRepository.findMinProductQuantity();
//        assertEquals(1L, minProductQuantity.get().intValue());
//    }
//
//    @Test
//    void findMeanOpportunitiesPerAccount() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
//
//        var testOpp1 = new OpportunityRequestDTO(Truck.FLATBED, 10, contactRequestDTOS.get(0), salesRepRequestDTOS.get(0));
//        opportunityRepository.save(testOpp1);
//        var testOpp2 = new OpportunityRequestDTO(Truck.BOX, 1150, contactRequestDTOS.get(1), salesRepRequestDTOS.get(0));
//        opportunityRepository.save(testOpp2);
//        var testOpp3 = new OpportunityRequestDTO(Truck.HYBRID, 1, contactRequestDTOS.get(2), salesRepRequestDTOS.get(1));
//        opportunityRepository.save(testOpp3);
//        var testAccount1 = new AccountRequestDTO(Industry.PRODUCE, 50, "London", "UNITED KINGDOM", contactRequestDTOS.get(0), opportunities.get(0));
//        accountRepository.save(testAccount1);
//        testAccount1.addOpportunity(testOpp1);
//        testAccount1.addOpportunity(testOpp2);
//        testAccount1.addOpportunity(testOpp3);
//        accountRepository.save(testAccount1);
//        testOpp1.setAccountRequestDTO(testAccount1);
//        testOpp2.setAccountRequestDTO(testAccount1);
//        testOpp3.setAccountRequestDTO(testAccount1);
//        opportunityRepository.save(testOpp1);
//        opportunityRepository.save(testOpp2);
//        opportunityRepository.save(testOpp3);
//
//        var meanOppsPerAccount = opportunityRepository.findMeanOpportunitiesPerAccount();
//        assertEquals(1.5, meanOppsPerAccount.get().doubleValue());
//    }
//
//    @Test
//    void findMedianOppsPerAccountStep1_Test(){
//        var medianAccountsFirstStep = opportunityRepository.findMedianOppsPerAccountStep1();
//        assertEquals(3, medianAccountsFirstStep.length);
//    }
//
//    @Test
//    void findMaxOpportunitiesPerAccount() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
//        var testOpp1 = new OpportunityRequestDTO(Truck.FLATBED, 10, contactRequestDTOS.get(0), salesRepRequestDTOS.get(0));
//        var testOpp2 = new OpportunityRequestDTO(Truck.BOX, 1150, contactRequestDTOS.get(1), salesRepRequestDTOS.get(0));
//        var testOpp3 = new OpportunityRequestDTO(Truck.HYBRID, 1, contactRequestDTOS.get(2), salesRepRequestDTOS.get(1));
//        var testAccount1 = new AccountRequestDTO(Industry.PRODUCE, 50, "London", "UNITED KINGDOM", contactRequestDTOS.get(0), opportunities.get(0));
//        testAccount1.addOpportunity(testOpp1);
//        testAccount1.addOpportunity(testOpp2);
//        testAccount1.addOpportunity(testOpp3);
//        accountRepository.save(testAccount1);
//        testOpp1.setAccountRequestDTO(testAccount1);
//        testOpp2.setAccountRequestDTO(testAccount1);
//        testOpp3.setAccountRequestDTO(testAccount1);
//        opportunityRepository.save(testOpp1);
//        opportunityRepository.save(testOpp2);
//        opportunityRepository.save(testOpp3);
//
//        var maxOppsPerAccount = opportunityRepository.findMaxOpportunitiesPerAccount();
//        assertEquals(3, maxOppsPerAccount.get().doubleValue());
//    }
//
//    @Test
//    void findMinOpportunitiesPerAccount() throws ExceedsMaxLength, NameContainsNumbersException, EmptyStringException, InvalidCountryException {
//        var testOpp1 = new OpportunityRequestDTO(Truck.FLATBED, 10, contactRequestDTOS.get(0), salesRepRequestDTOS.get(0));
//        var testOpp2 = new OpportunityRequestDTO(Truck.BOX, 1150, contactRequestDTOS.get(1), salesRepRequestDTOS.get(0));
//        var testOpp3 = new OpportunityRequestDTO(Truck.HYBRID, 1, contactRequestDTOS.get(2), salesRepRequestDTOS.get(1));
//        var testAccount1 = new AccountRequestDTO(Industry.PRODUCE, 50, "London", "UNITED KINGDOM", contactRequestDTOS.get(0), opportunities.get(0));
//        testAccount1.addOpportunity(testOpp1);
//        testAccount1.addOpportunity(testOpp2);
//        testAccount1.addOpportunity(testOpp3);
//        accountRepository.save(testAccount1);
//        testOpp1.setAccountRequestDTO(testAccount1);
//        testOpp2.setAccountRequestDTO(testAccount1);
//        testOpp3.setAccountRequestDTO(testAccount1);
//        opportunityRepository.save(testOpp1);
//        opportunityRepository.save(testOpp2);
//        opportunityRepository.save(testOpp3);
//        var minOppsPerAccount = opportunityRepository.findMinOpportunitiesPerAccount();
//        assertEquals(1, minOppsPerAccount.get().doubleValue());
//    }
//
//
//
//}