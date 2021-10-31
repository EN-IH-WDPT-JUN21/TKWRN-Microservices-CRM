package com.ironhack.leadservice.proxy;

import com.ironhack.leadservice.dto.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("opportunity-service")
public interface OpportunityProxy {

    @PostMapping("/api/v1/opportunities/create")
    OpportunityDTO createOpportunity(@RequestBody OpportunityDTO newOpp);
}
