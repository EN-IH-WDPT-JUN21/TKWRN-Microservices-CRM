package com.ironhack.ReportService.Controller;

import com.ironhack.ReportService.Service.DatabasePopulationService;
import com.ironhack.ReportService.dao.*;
import com.ironhack.ReportService.dto.AccountDTO;
import com.ironhack.ReportService.dto.OpportunityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report-db")
public class DatabaseController {

    @Autowired
    DatabasePopulationService dbpService;

    //Full Mirror Methods, use sparringly!
    @PostMapping("/post/accounts")
    public String createAccountDatabase(@RequestBody List<AccountDTO> accountDTOList){
        return dbpService.createAccountDatabase(accountDTOList);
    }
    @PostMapping("/post/contacts")
    public String createContactDatabase(@RequestBody List<Contact> contactList){
        return dbpService.createContactDatabase(contactList);
    }
    @PostMapping("/post/leads")
    public String createLeadDatabase(@RequestBody List<Lead> leadList){
        return dbpService.createLeadDatabase(leadList);
    }
    @PostMapping("/post/opportunities")
    public String createOpportunityDatabase(@RequestBody List<OpportunityDTO> opportunityDTOList){
        return dbpService.createOppDatabase(opportunityDTOList);
    }
    @PostMapping("/post/salesreps")
    public String createSalesrepDatabase(@RequestBody List<SalesRep> salesrepList){
        return dbpService.createSalesrepDatabase(salesrepList);
    }


    //Database Entry Post Methods
    @PostMapping("/new/account")
    public Account addorCreateAccount(@RequestBody Account account){
        return dbpService.addOrCreateAccount(account);
    }
    @PostMapping("/new/contact")
    public Contact addorCreateContact(@RequestBody Contact contact){
        return dbpService.addOrCreateContact(contact);
    }
    @PostMapping("/new/lead")
    public Lead addOrCreateLead(@RequestBody Lead lead){
        return dbpService.addOrCreateLead(lead);
    }
    @PostMapping("/new/opportunity")
    public Opportunity addOrCreateOpp(@RequestBody OpportunityDTO opportunityDTO){
        return dbpService.addOrCreateOpp(opportunityDTO);
    }
    @PostMapping("/new/salesrep")
    public SalesRep addOrCreateSalesRep(@RequestBody SalesRep salesrep){
        return dbpService.addOrCreateSalesRep(salesrep);
    }

}
