package com.ironhack.ReportService.Service;

import com.ironhack.ReportService.dao.*;
import com.ironhack.ReportService.dto.AccountDTO;
import com.ironhack.ReportService.dto.OpportunityDTO;
import com.ironhack.ReportService.dto.SalesRepDTO;
import com.ironhack.ReportService.repository.*;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DatabasePopulationService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    SalesRepRepository salesRepRepository;

    public String createAccountDatabase(List<AccountDTO> accountDTOList) {
        List<Account> accountList = new ArrayList<>();
        for (var accountDTO : accountDTOList){
            accountList.add(new Account(accountDTO));
        }
        try {
            accountRepository.saveAll(accountList);
        } catch (Exception e){
            throw new IllegalArgumentException("Malformed Entries passed to Database");
        }
        accountRepository.deleteAll();
        accountRepository.saveAll(accountList);
        return "Mirrored " + accountDTOList.size() + " entries to Account Database";
    }

    public String createContactDatabase(List<Contact> contactList) {
        try {
            contactRepository.saveAll(contactList);
        } catch (Exception e){
            throw new IllegalArgumentException("Malformed Entries passed to Database");
        }
        contactRepository.deleteAll();
        contactRepository.saveAll(contactList);
        return "Mirrored " + contactList.size() + " entries to Contact Database";
    }

    public String createLeadDatabase(List<Lead> leadList) {
        try {
            leadRepository.saveAll(leadList);
        } catch (Exception e){
            throw new IllegalArgumentException("Malformed Entries passed to Database");
        }
        leadRepository.deleteAll();
        leadRepository.saveAll(leadList);
        return "Mirrored " + leadList.size() + " entries to Lead Database";
    }

    public String createOppDatabase(List<OpportunityDTO> opportunityDTOList) {
        List<Opportunity> opportunityList = new ArrayList<>();
        for (var opportunityDTO : opportunityDTOList){
            try {
                opportunityList.add(new Opportunity(opportunityDTO));
            } catch (Exception e){
                throw new IllegalArgumentException("Error finding Account or Salesrep referenced in Opportunities." +
                        "Please ensure the Sales Rep and Account databases are up to date before retrying");
            }
        }
        try {
            opportunityRepository.saveAll(opportunityList);
        } catch (Exception e){
            throw new IllegalArgumentException("Malformed Entries passed to Database");
        }
        opportunityRepository.deleteAll();
        opportunityRepository.saveAll(opportunityList);
        return "Mirrored " + opportunityList.size() + " entries to Opportunity Database";
    }

    public String createSalesrepDatabase(List<SalesRepDTO> salesrepDTOList) {
        List<SalesRep> salesrepList = new ArrayList<>();
        for (SalesRepDTO salesrepDTO : salesrepDTOList) {
            try {
                salesrepList.add(new SalesRep(salesrepDTO));
            } catch (Exception e){
                throw new IllegalArgumentException("Error finding  Salesrep");
            }
        }
        try {
            salesRepRepository.saveAll(salesrepList);
        } catch (Exception e){
            throw new IllegalArgumentException("Malformed Entries passed to Database");
        }
        salesRepRepository.deleteAll();
        salesRepRepository.saveAll(salesrepList);
        return "Mirrored " + salesrepList.size() + " entries to Salesrep Database";
    }


    public Account addOrUpdateAccount(Account account) {
        try {
            accountRepository.save(account);
        } catch (Exception e) {
            throw new IllegalArgumentException("Malformed account Passed to Database");
        }
        return account;
    }

    public Contact addOrUpdateContact(Contact contact) {
        try {
            contactRepository.save(contact);
        } catch (Exception e) {
            throw new IllegalArgumentException("Malformed contact Passed to Database");
        }
        return contact;
    }

    public Lead addOrUpdateLead(Lead lead) {
        try {
            leadRepository.save(lead);
        } catch (Exception e) {
            throw new IllegalArgumentException("Malformed lead Passed to Database");
        }
        return lead;
    }

    public Opportunity addOrUpdateOpp(OpportunityDTO opportunityDTO) {
        Opportunity opportunity = new Opportunity(opportunityDTO);
        try {
            opportunityRepository.save(opportunity);
        } catch (Exception e) {
            throw new IllegalArgumentException("Malformed opportunity Passed to Database");
        }
        return opportunity;
    }

    public SalesRep addOrUpdateSalesRep(SalesRep salesrep) {
        try {
            salesRepRepository.save(salesrep);
        } catch (Exception e) {
            throw new IllegalArgumentException("Malformed SalesRep Passed to Database");
        }
        return salesrep;
    }
}
