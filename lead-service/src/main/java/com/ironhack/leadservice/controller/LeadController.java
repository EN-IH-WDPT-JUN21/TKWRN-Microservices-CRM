package com.ironhack.leadservice.controller;

import com.ironhack.leadservice.repository.LeadRepository;
import com.ironhack.leadservice.service.LeadService;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.dto.OpportunityDTO;
import com.ironhack.leadservice.enums.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeadController {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    LeadService leadService;

    @GetMapping("/api/v1/leads")
    @ResponseStatus(HttpStatus.OK)
    public List<LeadDTO> getLeads() {
        return leadService.getAllLeads();
    }

    @GetMapping("/api/v1/leads/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadDTO getLeadById(@PathVariable(value = "id") long id) {
        return leadService.getById(id);
    }

    @PostMapping("/api/v1/leads/new")
    @ResponseStatus(HttpStatus.CREATED)
    public LeadDTO createLead(@RequestBody LeadDTO lead) {
        return leadService.create(lead);
    }

    @PostMapping("/api/v1/leads/populate")
    @ResponseStatus(HttpStatus.OK)
    public String createLeadDatabase() {
        return leadService.populateLeadDatabase();
    }

    @GetMapping("/api/v1/leads/convert/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OpportunityDTO convertLead(@PathVariable(value = "id") long id,
                                      @RequestParam Truck product,
                                      @RequestParam int quantity) {
        return leadService.convert(id, product, quantity);
    }

    @DeleteMapping("/api/v1/leads/delete/{id}")
    public void delete(@PathVariable(value = "id") long id,
                       @RequestBody LeadDTO lead) {
         leadService.delete(id, lead);
    }
}
