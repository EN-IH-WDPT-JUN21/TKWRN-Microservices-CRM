package com.ironhack.salesrepservice.service.impl;

import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.SalesRepDTO;
import com.ironhack.salesrepservice.proxy.LeadServiceProxy;
import com.ironhack.salesrepservice.proxy.OpportunityServiceProxy;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import com.ironhack.salesrepservice.service.interfaces.ISalesRepService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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


    public SalesRepDTO convertSalesRepToDTO(SalesRep salesRep) {
        return new SalesRepDTO(salesRep.getId(),
                               salesRep.getRepName(),
                               leadServiceProxy.findLeadsBySalesRep(salesRep.getId()),
                               opportunityServiceProxy.findOppsBySalesRep(salesRep.getId()));
    }


    public SalesRep convertDTOToSalesRep(SalesRepDTO salesRepDTO) {
        SalesRep salesRep = new SalesRep(salesRepDTO.getId(),
                                         salesRepDTO.getRepName());
        return salesRep;
    }
}
