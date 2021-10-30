package com.ironhack.OpportunityService.Controller;

import com.ironhack.OpportunityService.DTO.OpportunityDTO;
import com.ironhack.OpportunityService.Service.OpportunityService;
import com.ironhack.OpportunityService.dao.Opportunity;
import com.ironhack.OpportunityService.repository.OpportunityRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opp")
public class OppController {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    OpportunityService opportunityService;

    @GetMapping("/get")
    public List<Opportunity> getAll(){return opportunityRepository.findAll();}

    @GetMapping("/get/{id}")
    public Opportunity getById(@PathVariable Long id){
        return opportunityRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Opportunity createOpportunity(@RequestBody OpportunityDTO opportunityDTO){
        return opportunityService.createOpp(opportunityDTO);
    }

    @PutMapping("/update/{id}")
    public Opportunity updateOpportunity(@PathVariable Long id, @RequestBody OpportunityDTO opportunityDTO){
        return opportunityService.updateOpp(id, opportunityDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOpportunity(@PathVariable Long id){
        opportunityRepository.deleteById(id);
    }
}