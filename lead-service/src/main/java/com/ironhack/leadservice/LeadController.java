package com.ironhack.leadservice;

import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.dto.OpportunityDTO;
import com.ironhack.leadservice.enums.Status;
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

    @GetMapping("/leads")
    @ResponseStatus(HttpStatus.OK)
    public List<Lead> getLeads() {
        return leadRepository.findAll();
    }

    @GetMapping("/leads/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadDTO getLeadById(@PathVariable(value = "id") long id) {
        return leadService.getById(id);
    }

    @PostMapping("/leads")
    @ResponseStatus(HttpStatus.CREATED)
    public LeadDTO createLead(@RequestBody LeadDTO lead) {
        return leadService.create(lead);
    }

    @GetMapping("/leads/{id}convert")
    @ResponseStatus(HttpStatus.CREATED)
    public OpportunityDTO convertLead(@PathVariable(value = "id") long id,
                                      @RequestParam Truck product,
                                      @RequestParam int quantity,
                                      @RequestParam long contactId,
                                      @RequestParam long salesId) {

        return leadService.convert(id);
    }
}
