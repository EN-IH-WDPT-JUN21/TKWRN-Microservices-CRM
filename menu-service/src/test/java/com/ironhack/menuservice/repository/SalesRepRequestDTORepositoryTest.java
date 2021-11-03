//package com.ironhack.menuservice.repository;
//
//import com.ironhack.menuservice.StolenNameTruckingCompanyHomework3Application;
//import com.ironhack.menuservice.dto.SalesRepRequestDTO;
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
//class SalesRepRequestDTORepositoryTest {
//
//    @MockBean
//    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;
//
//    @Autowired
//    private SalesRepRepository salesRepRepository;
//
//    private List<SalesRepRequestDTO> salesRepRequestDTOS;
//
//    @BeforeEach
//    void setUp() {
//        salesRepRepository.deleteAll();
//        salesRepRequestDTOS = salesRepRepository.saveAll(List.of(
//                new SalesRepRequestDTO("David Lynch"),
//                new SalesRepRequestDTO("Martha Stewart")
//        ));
//    }
//
//    @AfterEach
//    void tearDown() { ;
//        salesRepRepository.deleteAll();
//        salesRepRequestDTOS.clear();
//    }
//
//    @Test
//    void findAllSalesreps_shouldWork() {
//        var salesRepsCount = salesRepRepository.findAllSalesReps().size();
//        assertEquals(2, salesRepsCount);
//    }
//
//
//}