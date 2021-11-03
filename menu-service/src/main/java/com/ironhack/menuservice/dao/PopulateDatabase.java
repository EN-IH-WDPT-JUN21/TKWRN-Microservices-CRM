package com.ironhack.menuservice.dao;

import com.ironhack.menuservice.dto.*;
import com.ironhack.menuservice.enums.Industry;
import com.ironhack.menuservice.enums.Truck;
import com.ironhack.menuservice.exceptions.*;
import com.ironhack.menuservice.proxy.ReportDBServiceProxy;

import java.util.List;

public class PopulateDatabase{

    private static ReportDBServiceProxy reportDBServiceProxy;

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
                new AccountRequestDTO( 1L, Industry.PRODUCE.toString(), 50, "London", "UNITED KINGDOM"),
                new AccountRequestDTO(2L, Industry.ECOMMERCE.toString(), 500, "Madrid", "SPAIN"),
                new AccountRequestDTO(3L, Industry.MANUFACTURING.toString(), 20, "Paris", "FRANCE")
        );

        List<OpportunityRequestDTO> opportunityRequestDTOList = List.of(
                new OpportunityRequestDTO(1L, Truck.FLATBED, 10, contactRequestDTOList.get(0).getId(), 1L,  salesRepRequestDTOList.get(0).getId()),
                new OpportunityRequestDTO(2L, Truck.BOX, 1150, contactRequestDTOList.get(1).getId(), 2L, salesRepRequestDTOList.get(0).getId()),
                new OpportunityRequestDTO(3L, Truck.HYBRID, 1, contactRequestDTOList.get(2).getId(), 3L, salesRepRequestDTOList.get(1).getId())

        );

        reportDBServiceProxy.createSalesrepDatabase(salesRepRequestDTOList);
        reportDBServiceProxy.createLeadDatabase(leadRequestDTOList);
        reportDBServiceProxy.createContactDatabase(contactRequestDTOList);
        reportDBServiceProxy.createAccountDatabase(accountRequestDTOList);
        reportDBServiceProxy.createOpportunityDatabase(opportunityRequestDTOList);
    }

}
