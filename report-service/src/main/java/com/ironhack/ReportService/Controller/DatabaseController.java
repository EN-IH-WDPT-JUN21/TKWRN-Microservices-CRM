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
    public Account addOrUpdateAccount(@RequestBody Account account){
        return dbpService.addOrUpdateAccount(account);
    }
    @PostMapping("/new/contact")
    public Contact addOrUpdateContact(@RequestBody Contact contact){
        return dbpService.addOrUpdateContact(contact);
    }
    @PostMapping("/new/lead")
    public Lead addOrUpdateLead(@RequestBody Lead lead){
        return dbpService.addOrUpdateLead(lead);
    }
    @PostMapping("/new/opportunity")
    public Opportunity addOrUpdateOpp(@RequestBody OpportunityDTO opportunityDTO){
        return dbpService.addOrUpdateOpp(opportunityDTO);
    }
    @PostMapping("/new/salesrep")
    public SalesRep addOrUpdateSalesRep(@RequestBody SalesRep salesrep){
        return dbpService.addOrUpdateSalesRep(salesrep);
    }

}
