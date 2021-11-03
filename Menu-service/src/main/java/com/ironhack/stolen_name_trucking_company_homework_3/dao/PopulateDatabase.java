package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.dto.*;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.proxy.ReportServiceProxy;

import java.util.List;

public class PopulateDatabase{

    private static ReportServiceProxy reportServiceProxy;

    public static void populateDatabase() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException, InvalidCountryException {

        List<SalesRepRequestDTO> salesRepRequestDTOList = List.of(
                new SalesRepRequestDTO(1L, "David Lynch"),
                new SalesRepRequestDTO(2L, "Martha Stewart")
        );

        List<LeadRequestDTO>  leadRequestDTOList= List.of(
                new LeadRequestDTO(1L, "Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", salesRepRequestDTOList.get(0).getId()),
                new LeadRequestDTO(2L, "Lee Dawson", "980651164", "ld@gmail.com", "LeeD", salesRepRequestDTOList.get(0).getId()),
                new LeadRequestDTO(3L, "Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland", salesRepRequestDTOList.get(1).getId())
        );

        List<ContactRequestDTO> contactRequestDTOList = List.of(
                new ContactRequestDTO(1L,"John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", salesRepRequestDTOList.get(0).getId()),
                new ContactRequestDTO(2L,"Martha Steward", "123475357", "ms@wp.pl", "My own company", salesRepRequestDTOList.get(1).getId()),
                new ContactRequestDTO(3L,"George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show", salesRepRequestDTOList.get(0).getId())
        );

        List<AccountRequestDTO> accountRequestDTOList = List.of(
                new AccountRequestDTO(1L, Industry.PRODUCE, 50, "London", "UNITED KINGDOM"),
                new AccountRequestDTO(2L, Industry.ECOMMERCE, 500, "Madrid", "SPAIN"),
                new AccountRequestDTO(3L, Industry.MANUFACTURING, 20, "Paris", "FRANCE")
        );

        List<OpportunityRequestDTO> opportunityRequestDTOList = List.of(
                new OpportunityRequestDTO(1L, Truck.FLATBED, 10, contactRequestDTOList.get(0).getId(), accountRequestDTOList.get(0).getId(),  salesRepRequestDTOList.get(0).getId()),
                new OpportunityRequestDTO(2L, Truck.BOX, 1150, contactRequestDTOList.get(1).getId(), accountRequestDTOList.get(1).getId(), salesRepRequestDTOList.get(0).getId()),
                new OpportunityRequestDTO(3L, Truck.HYBRID, 1, contactRequestDTOList.get(2).getId(), accountRequestDTOList.get(2).getId(), salesRepRequestDTOList.get(1).getId())

        );

        reportServiceProxy.createSalesrepDatabase(salesRepRequestDTOList);
        reportServiceProxy.createLeadDatabase(leadRequestDTOList);
        reportServiceProxy.createContactDatabase(contactRequestDTOList);
        reportServiceProxy.createAccountDatabase(accountRequestDTOList);
        reportServiceProxy.createOpportunityDatabase(opportunityRequestDTOList);
    }

}
