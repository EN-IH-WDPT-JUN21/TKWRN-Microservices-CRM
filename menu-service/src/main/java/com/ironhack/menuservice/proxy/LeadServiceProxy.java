package com.ironhack.menuservice.proxy;

import com.ironhack.menuservice.dto.LeadRequestDTO;
import com.ironhack.menuservice.enums.Truck;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("lead-service")
public interface LeadServiceProxy {

    @GetMapping("/api/v1/leads")
    @ResponseStatus(HttpStatus.OK)
    List<LeadRequestDTO> getLeads();

    @GetMapping("/api/v1/leads/{id}")
    @ResponseStatus(HttpStatus.OK)
    LeadRequestDTO getLeadById(@PathVariable(value = "id") long id);

    @PostMapping("/api/v1/leads/new")
    @ResponseStatus(HttpStatus.CREATED)
    LeadRequestDTO createLead(@RequestBody LeadRequestDTO lead);

    @GetMapping("/api/v1/leads/{id}/convert")
    @ResponseStatus(HttpStatus.CREATED)
    LeadRequestDTO convertLead(@PathVariable(value = "id") long id,
                                      @RequestParam Truck product,
                                      @RequestParam int quantity);

}
