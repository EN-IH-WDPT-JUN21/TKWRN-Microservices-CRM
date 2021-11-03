package com.ironhack.salesrepservice.service.impl;

import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.LeadDTO;
import com.ironhack.salesrepservice.dto.OpportunityDTO;
import com.ironhack.salesrepservice.dto.SalesRepDTO;
import com.ironhack.salesrepservice.proxy.LeadServiceProxy;
import com.ironhack.salesrepservice.proxy.OpportunityServiceProxy;
import com.ironhack.salesrepservice.proxy.ReportServiceProxy;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import com.ironhack.salesrepservice.service.interfaces.ISalesRepService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesRepService implements ISalesRepService {

    SalesRepRepository salesRepRepository;
    LeadServiceProxy leadServiceProxy;
    OpportunityServiceProxy opportunityServiceProxy;
    ReportServiceProxy reportServiceProxy;

    public SalesRepService(SalesRepRepository salesRepRepository, LeadServiceProxy leadServiceProxy,
                           OpportunityServiceProxy opportunityServiceProxy, ReportServiceProxy reportServiceProxy) {
        this.salesRepRepository = salesRepRepository;
        this.leadServiceProxy = leadServiceProxy;
        this.opportunityServiceProxy = opportunityServiceProxy;
        this.reportServiceProxy = reportServiceProxy;
    }


    public SalesRepDTO addSalesRep(SalesRepDTO salesRepDTO) {
        SalesRep salesRep = convertDTOToSalesRep(salesRepDTO);
        salesRepRepository.save(salesRep);
        reportServiceProxy.addOrUpdateSalesRep(salesRepDTO);
        return convertSalesRepToDTO(salesRep);
    }

    public SalesRepDTO findById(Long id) {
        SalesRep salesRep = salesRepRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales representative with id " + id + " does not exist."));
        return convertSalesRepToDTO(salesRep);
    }

    public List<SalesRepDTO> findAllSalesReps() {
        List<SalesRepDTO> salesRepDTOList = new ArrayList<>();
        for(SalesRep salesRep : salesRepRepository.findAll()) {
            SalesRepDTO salesRepDTO = convertSalesRepToDTO(salesRep);
            salesRepDTOList.add(salesRepDTO);
        }
        return salesRepDTOList;
    }

    public SalesRepDTO updateSalesRepName(Long id, String name) {
        SalesRep salesRep = salesRepRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales representative with id " + id + " does not exist."));
        salesRep.setRepName(name);
        salesRepRepository.save(salesRep);
        SalesRepDTO salesRepDTO = convertSalesRepToDTO(salesRep);
        reportServiceProxy.addOrUpdateSalesRep(salesRepDTO);
        return salesRepDTO;
    }

    public void deleteSalesRep(Long id) {
        SalesRep salesRep = salesRepRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales representative with id " + id + " does not exist."));
        salesRepRepository.delete(salesRep);
    }


    public String populateSalesrepDatabase() {
        List<SalesRepDTO> salesrepDTOList = new ArrayList<>(findAllSalesReps());
        return reportServiceProxy.createSalesrepDatabase(salesrepDTOList);
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
        return new SalesRep(salesRepDTO.getId(),
                            salesRepDTO.getRepName());
    }
}
