package com.ironhack.leadservice.service;

import com.ironhack.leadservice.enums.Status;
import com.ironhack.leadservice.enums.Truck;
import com.ironhack.leadservice.proxies.ContactProxy;
import com.ironhack.leadservice.repository.LeadRepository;
import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.dto.ContactDTO;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.dto.OpportunityDTO;
import com.ironhack.leadservice.dto.SalesRepDTO;
import com.ironhack.leadservice.proxies.OpportunityProxy;
import com.ironhack.leadservice.proxies.SalesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    private LeadRepository leadRepository;

    private SalesProxy salesProxy;

    private OpportunityProxy oops;

    private ContactProxy contactProxy;

    public LeadService(LeadRepository leadRepository, SalesProxy salesProxy, OpportunityProxy oops, ContactProxy contactProxy) {
        this.leadRepository = leadRepository;
        this.salesProxy = salesProxy;
        this.oops = oops;
        this.contactProxy = contactProxy;
    }

    public LeadDTO getById(long id) {
        Lead lead = leadRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead with id " + id + " does not exist."));
        LeadDTO leadDTO = convertLeadToDto(lead);
        return leadDTO;
    }

    public LeadDTO create(LeadDTO leadDTO) {
        Optional<Lead> lead = leadRepository.findById(leadDTO.getId());
        if(lead.isEmpty()) {
            try {
                SalesRepDTO sales = salesProxy.getSales(leadDTO.getSalesId());
                Lead newLead = convertDtoToLead(leadDTO);
                newLead.setSalesId(sales.getId());
                leadRepository.save(newLead);
                return convertLeadToDto(newLead);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The sales id wasn't found in the system.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The lead id already exists in the system.");
        }
    }

    public LeadDTO convertLeadToDto(Lead lead) {
        LeadDTO newLeadDto = new LeadDTO(lead.getId(), lead.getName(),
                lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName(), lead.getSalesId());
        return newLeadDto;
    }

    public Lead convertDtoToLead(LeadDTO leadDTO) {
        Lead newLead = new Lead(leadDTO.getId(), leadDTO.getName(),
                leadDTO.getPhoneNumber(), leadDTO.getEmail(), leadDTO.getCompanyName(), leadDTO.getSalesId());
        return newLead;
    }

    public OpportunityDTO convert(Long id, Truck product, int quantity) {
        LeadDTO leadDTO = getById(id);
        ContactDTO newContact = contactProxy.createContact(leadDTO);
        OpportunityDTO newOpp = new OpportunityDTO(Status.OPEN, product, quantity, newContact.getId(), leadDTO.getSalesId());
//        oops.createOpportunity(newOpp);
        Lead lead = convertDtoToLead(leadDTO);
        leadRepository.delete(lead);
        return newOpp;
    }

    public List<LeadDTO> getAllLeads() {
        List<Lead> allLeads = leadRepository.findAll();
        List<LeadDTO> allLeadDTO = new ArrayList<>();
        for(Lead lead: allLeads) {
            allLeadDTO.add(convertLeadToDto(lead));
        }
        return  allLeadDTO;
    }
}
