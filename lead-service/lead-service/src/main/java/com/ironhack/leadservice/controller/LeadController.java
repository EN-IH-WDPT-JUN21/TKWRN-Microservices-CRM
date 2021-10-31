package com.ironhack.leadservice.controller;

import com.ironhack.leadservice.repository.LeadRepository;
import com.ironhack.leadservice.service.LeadService;
import org.springframework.web.bind.annotation.RestController;

import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.dto.OpportunityDTO;
import com.ironhack.leadservice.enums.Truck;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leads")
public class LeadController {

    private LeadRepository leadRepository;
    private LeadService leadService;

    public LeadController(LeadRepository leadRepository, LeadService leadService) {
        this.leadRepository = leadRepository;
        this.leadService = leadService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LeadDTO> getLeads() {
        return leadService.getAllLeads();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadDTO getLeadById(@PathVariable(value = "id") long id) {
        return leadService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeadDTO createLead(@RequestBody LeadDTO lead) {
        return leadService.create(lead);
    }

    @GetMapping("/{id}/convert")
    @ResponseStatus(HttpStatus.OK)
    public OpportunityDTO convertLead(@PathVariable(value = "id") long id,
                                      @RequestParam Truck product,
                                      @RequestParam int quantity) {


        return leadService.convert(id, product, quantity);
    }
}

