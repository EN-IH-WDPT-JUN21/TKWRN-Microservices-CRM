package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.dto.ContactRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.OpportunityRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class OpportunityRequestDTOTest {

    OpportunityRequestDTO opp;

    @BeforeEach
    void setUp() throws ExceedsMaxLength, NameContainsNumbersException, EmptyStringException, EmailNotValidException,
            PhoneNumberContainsLettersException {
         opp = new OpportunityRequestDTO(Truck.BOX, 200, new ContactRequestDTO("TestOne", "123546",
                "test1@test.gmail.com", "TestCompany1"));
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void setQuantity_shouldWork() throws ExceedsMaxLength {
        opp.setQuantity(23);
        assertEquals(23, opp.getQuantity());
    }

    @Test
    void setQuantity_throwsException_IllegalArgument() {
        Assert.assertThrows(IllegalArgumentException.class, () -> { opp.setQuantity(-3);});
    }


}