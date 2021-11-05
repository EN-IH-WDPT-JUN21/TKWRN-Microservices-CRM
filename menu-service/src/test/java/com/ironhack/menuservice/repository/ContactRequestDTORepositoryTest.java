//package com.ironhack.menuservice.repository;
//
//import com.ironhack.menuservice.StolenNameTruckingCompanyHomework3Application;
//import com.ironhack.menuservice.dto.ContactRequestDTO;
//import com.ironhack.menuservice.dto.SalesRepRequestDTO;
//
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
//class ContactRequestDTORepositoryTest {
//
//    @MockBean
//    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;
//
//    @Autowired
//    private ContactRepository contactRepository;
//
//    @Autowired
//    private SalesRepRepository salesRepRepository;
//
//    private List<SalesRepRequestDTO> salesRepRequestDTOS;
//    private List<ContactRequestDTO> contactRequestDTOS;
//
//
//    @BeforeEach
//    void setUp()  {
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
//        ));
//    }
//
//    @AfterEach
//    void tearDown() {
//        contactRepository.deleteAll();
//
//    }
//
//    @Test
//    void findAllContacts_shouldWork(){
//        var contactCount = contactRepository.findAllContacts().size();
//        assertEquals(3, contactCount);
//    }
//
//    @Test
//    void findById_shouldWork(){
//        var contact = contactRepository.findById(contactRequestDTOS.get(1).getId());
//        assertEquals("123475357", contact.get().getPhoneNumber());
//    }
//
//}