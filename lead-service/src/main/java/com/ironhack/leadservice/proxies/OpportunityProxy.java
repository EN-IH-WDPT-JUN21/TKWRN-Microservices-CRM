package com.ironhack.leadservice.proxies;

import com.ironhack.leadservice.dto.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("opportunity-service")
public interface OpportunityProxy {

    @GetMapping("/api/v1/opps/{id}")
    OpportunityDTO getOpportunity(@PathVariable(value = "id") long id);

    @PostMapping("/api/v1/opps")
    OpportunityDTO createOpportunity(@RequestBody OpportunityDTO opps);
}
