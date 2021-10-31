package com.ironhack.ReportService.Controller;

import com.ironhack.ReportService.Service.DatabasePopulationService;
import com.ironhack.ReportService.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class DatabaseController {

    @Autowired
    DatabasePopulationService dbpService;

    //Full Mirror Methods, use sparringly!
    @PostMapping("/post/accounts")
    public String createAccountDatabase(@RequestBody List<Account> accountList){
        return dbpService.createAccountDatabase(accountList);
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
    public String createOpportunityDatabase(@RequestBody List<Opportunity> opportunityList){
        return dbpService.createOppDatabase(opportunityList);
    }
    @PostMapping("/post/salesreps")
    public String createSalesrepDatabase(@RequestBody List<SalesRep> salesrepList){
        return dbpService.createSalesrepDatabase(salesrepList);
    }


    //Database Entry Post Methods
    @PostMapping("/post/accounts/add")
    public Account addOrUpdateAccount(@RequestBody Account account){
        return dbpService.addOrUpdateAccount(account);
    }
    @PostMapping("/post/contacts/add")
    public Contact addOrUpdateContact(@RequestBody Contact contact){
        return dbpService.addOrUpdateContact(contact);
    }
    @PostMapping("/post/leads/add")
    public Lead addOrUpdateLead(@RequestBody Lead lead){
        return dbpService.addOrUpdateLead(lead);
    }
    @PostMapping("/post/opportunities/add")
    public Opportunity addOrUpdateOpp(@RequestBody Opportunity opportunity){
        return dbpService.addOrUpdateOpp(opportunity);
    }
    @PostMapping("/post/salesreps/add")
    public SalesRep addOrCreateSalesRep(@RequestBody SalesRep salesrep){
        return dbpService.addOrUpdateSalesRep(salesrep);
    }

}
