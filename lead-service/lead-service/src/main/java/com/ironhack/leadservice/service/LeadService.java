package com.ironhack.leadservice.service;

import com.ironhack.contactservice.dto.ContactDTO;
import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.dto.OpportunityDTO;
import com.ironhack.leadservice.dto.SalesRepDTO;
import com.ironhack.leadservice.enums.Status;
import com.ironhack.leadservice.enums.Truck;
import com.ironhack.leadservice.proxy.ContactProxy;
import com.ironhack.leadservice.proxy.OpportunityProxy;
import com.ironhack.leadservice.proxy.SalesProxy;
import com.ironhack.leadservice.repository.LeadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    private SalesProxy salesProxy;

    private LeadRepository leadRepository;

    private OpportunityProxy oops;

    private ContactProxy contactProxy;

    public LeadService(SalesProxy salesProxy, LeadRepository leadRepository,
                       OpportunityProxy oops, ContactProxy contactProxy) {
        this.salesProxy = salesProxy;
        this.leadRepository = leadRepository;
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

    public OpportunityDTO convert(long id, Truck product, int quantity) {
        LeadDTO leadDTO = getById(id);
        ContactDTO contactDTO = contactProxy.createContact(leadDTO);
        OpportunityDTO newOpp = new OpportunityDTO(Status.OPEN, product, quantity, leadDTO.getSalesId(), contactDTO.getId());
//        salesProxy.create(newOpp);
        Lead lead = convertDtoToLead(leadDTO);
        leadRepository.delete(lead);
        return newOpp;
    }

    public List<LeadDTO> getAllLeads() {
        List<Lead> leadList = leadRepository.findAll();
        List<LeadDTO> leadDTOList = new ArrayList<>();
        for(Lead lead: leadList) {
            leadDTOList.add(convertLeadToDto(lead));
        }
        return leadDTOList;
    }
}
