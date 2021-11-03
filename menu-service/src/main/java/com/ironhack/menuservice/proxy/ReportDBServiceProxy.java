package com.ironhack.menuservice.proxy;

import com.ironhack.menuservice.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("report-service")
public interface ReportDBServiceProxy {

    @PostMapping("/post/accounts")
    public String createAccountDatabase(@RequestBody List<AccountRequestDTO> accountDTOList);
    @PostMapping("/post/contacts")
    public String createContactDatabase(@RequestBody List<ContactRequestDTO> contactDTOList);
    @PostMapping("/post/leads")
    public String createLeadDatabase(@RequestBody List<LeadRequestDTO> leadDTOList);
    @PostMapping("/post/opportunities")
    public String createOpportunityDatabase(@RequestBody List<OpportunityRequestDTO> opportunityDTOList);
    @PostMapping("/post/salesreps")
    public String createSalesrepDatabase(@RequestBody List<SalesRepRequestDTO> salesrepDTOList);


    //Database Entry Post Methods
    @PostMapping("/new/account")
    public AccountRequestDTO addOrUpdateAccount(@RequestBody AccountRequestDTO account);
    @PostMapping("/new/contact")
    public ContactRequestDTO addOrUpdateContact(@RequestBody ContactRequestDTO contact);
    @PostMapping("/new/lead")
    public LeadRequestDTO addOrUpdateLead(@RequestBody LeadRequestDTO lead);
    @PostMapping("/new/opportunity")
    public OpportunityRequestDTO addOrUpdateOpp(@RequestBody OpportunityRequestDTO opportunityDTO);
    @PostMapping("/new/salesrep")
    public SalesRepRequestDTO addOrUpdateSalesRep(@RequestBody SalesRepRequestDTO salesrep);
}

