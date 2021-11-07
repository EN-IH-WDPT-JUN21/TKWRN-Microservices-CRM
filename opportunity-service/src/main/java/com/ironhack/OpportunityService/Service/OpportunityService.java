package com.ironhack.OpportunityService.Service;

import com.ironhack.OpportunityService.DTO.OpportunityDTO;
import com.ironhack.OpportunityService.dao.Opportunity;
import com.ironhack.OpportunityService.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService {

    @Autowired
    OpportunityRepository opportunityRepository;

    public Opportunity createOpp(OpportunityDTO opportunityDTO) {
        Opportunity newOpp =  new Opportunity(
                opportunityDTO.getStatus(),
                opportunityDTO.getProduct(),
                opportunityDTO.getQuantity(),
                opportunityDTO.getDecisionMakerId(),
                opportunityDTO.getAccountId(),
                opportunityDTO.getSalesRepId()
        );
        return opportunityRepository.save(newOpp);
    }

    public Opportunity updateOpp(Long id, OpportunityDTO opportunityDTO) {
        Optional<Opportunity> storedOpp = opportunityRepository.findById(id);
        if (storedOpp.isPresent()) {
            Opportunity presentOpp = storedOpp.get();
            if (opportunityDTO.getStatus() != null) {
                try {
                    presentOpp.setStatus(opportunityDTO.getStatus());
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status formatted incorrectly.");
                }
            }
            if (opportunityDTO.getProduct() != null) {
                try {
                    presentOpp.setProduct(opportunityDTO.getProduct());
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product formatted incorrectly.");
                }
            }
            if (opportunityDTO.getQuantity() != null) {
                try {
                    presentOpp.setQuantity(opportunityDTO.getQuantity());
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity formatted incorrectly.");
                }
            }
            if (opportunityDTO.getDecisionMakerId() != null) {
                try {
                    presentOpp.setDecisionMakerId(opportunityDTO.getDecisionMakerId());
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Decision Maker Id formatted incorrectly.");
                }
            }
            if (opportunityDTO.getAccountId() != null) {
                try {
                    presentOpp.setAccountId(opportunityDTO.getAccountId());
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account Id formatted incorrectly.");
                }
            }
            if (opportunityDTO.getSalesRepId() != null) {
                try {
                    presentOpp.setSalesRepId(opportunityDTO.getSalesRepId());
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sales Rep Id formatted incorrectly.");
                }
            }
            return opportunityRepository.save(presentOpp);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Opportunity with that Id Exists. Did you mean to create one?");
        }
    }

    public void updateAccountId(Long id, OpportunityDTO opportunityDTO) {
        Optional<Opportunity> storedOpp = opportunityRepository.findById(id);
        if (storedOpp.isPresent()){
            try {
                storedOpp.get().setAccountId(opportunityDTO.getAccountId());
                opportunityRepository.save(storedOpp.get());
            } catch (Exception e){
                throw new IllegalArgumentException("Malformed OpportunityDTO passed to updateAccount method.");
            }
        }
    }

    public List<Opportunity> findAllByAccountId(Long id) {
        List<Opportunity> oppList = opportunityRepository.findAllByAccountId(id);
        List<OpportunityDTO> oppDTOList = new ArrayList<>();
        for (var opp : oppList){
            oppDTOList.add(new OpportunityDTO(opp.getStatus(), opp.getProduct(), opp.getQuantity(),
                    opp.getDecisionMakerId(), opp.getAccountId(), opp.getSalesRepId()));
        }
        return oppList;
    }
}
