package com.ironhack.stolen_name_trucking_company_homework_3.proxy;

import com.ironhack.stolen_name_trucking_company_homework_3.dto.LeadRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("lead-service")
@RequestMapping("/api/v1/leads")
public interface LeadServiceProxy {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LeadRequestDTO> getLeads();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadRequestDTO getLeadById(@PathVariable(value = "id") long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeadRequestDTO createLead(@RequestBody LeadRequestDTO lead);

    @GetMapping("/{id}/convert")
    @ResponseStatus(HttpStatus.CREATED)
    public LeadRequestDTO convertLead(@PathVariable(value = "id") long id,
                                      @RequestParam Truck product,
                                      @RequestParam int quantity);

}
