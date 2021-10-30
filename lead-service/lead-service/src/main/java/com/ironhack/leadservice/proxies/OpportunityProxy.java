package com.ironhack.leadservice.proxies;

import com.ironhack.leadservice.dto.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("oops-service")
public interface OpportunityProxy {

    @GetMapping("/oops/{id}")
    OpportunityDTO getOpportunity(@PathVariable(value = "id") long id);

    @PostMapping("/oops")
    OpportunityDTO createOpportunity(@RequestBody OpportunityDTO oops);
}
