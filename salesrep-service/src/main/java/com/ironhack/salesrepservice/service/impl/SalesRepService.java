package com.ironhack.salesrepservice.service.impl;

import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.LeadDTO;
import com.ironhack.salesrepservice.dto.OpportunityDTO;
import com.ironhack.salesrepservice.dto.SalesRepDTO;
import com.ironhack.salesrepservice.proxy.LeadServiceProxy;
import com.ironhack.salesrepservice.proxy.OpportunityServiceProxy;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import com.ironhack.salesrepservice.service.interfaces.ISalesRepService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesRepService implements ISalesRepService {

    SalesRepRepository salesRepRepository;
    LeadServiceProxy leadServiceProxy;
    OpportunityServiceProxy opportunityServiceProxy;

    public SalesRepService(SalesRepRepository salesRepRepository, LeadServiceProxy leadServiceProxy,
                           OpportunityServiceProxy opportunityServiceProxy) {
        this.salesRepRepository = salesRepRepository;
        this.leadServiceProxy = leadServiceProxy;
        this.opportunityServiceProxy = opportunityServiceProxy;
    }


    public SalesRepDTO addSalesRep(SalesRepDTO salesRepDTO) {
        SalesRep salesRep = convertDTOToSalesRep(salesRepDTO);
        salesRepRepository.save(salesRep);
        SalesRepDTO newSalesRepDTO = convertSalesRepToDTO(salesRep);
        return newSalesRepDTO;
    }

    public SalesRepDTO findById(Long id) {
        Optional<SalesRep> tempSalesRep = salesRepRepository.findById(id);
        SalesRepDTO salesRepDTO = convertSalesRepToDTO(tempSalesRep.get());
        return salesRepDTO;
    }

    public List<SalesRepDTO> findAllSalesReps() {
        List<SalesRepDTO> salesRepDTOList = new ArrayList<>();
        for(SalesRep salesRep : salesRepRepository.findAll()) {
            SalesRepDTO salesRepDTO = convertSalesRepToDTO(salesRep);
            salesRepDTOList.add(salesRepDTO);
        }
        return salesRepDTOList;
    }


    public SalesRepDTO convertSalesRepToDTO(SalesRep salesRep) {
        List<Long> leadList = new ArrayList<>();
        List<Long> oppsList = new ArrayList<>();

        for(LeadDTO lead : leadServiceProxy.getAllLeads()) {
            if (lead.getSalesId() != null && lead.getSalesId().equals(salesRep.getId())) {
                leadList.add(lead.getId());
            }
        }

        for(OpportunityDTO opp : opportunityServiceProxy.getAllOpportunities()) {
            if(opp.getSalesRepId() != null && opp.getSalesRepId().equals(salesRep.getId())) {
                oppsList.add(opp.getId());
            }
        }

        return new SalesRepDTO(salesRep.getId(),
                               salesRep.getRepName(),
                               leadList,
                               oppsList);
    }


    public SalesRep convertDTOToSalesRep(SalesRepDTO salesRepDTO) {
        SalesRep salesRep = new SalesRep(salesRepDTO.getId(),
                                         salesRepDTO.getRepName());
        return salesRep;
    }
}
