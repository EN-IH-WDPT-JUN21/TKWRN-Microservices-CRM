package com.ironhack.salesrepservice.proxy;

import com.ironhack.salesrepservice.dto.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("opportunity-service")
public interface OpportunityServiceProxy {

    @GetMapping("/opp/get")
    List<OpportunityDTO> getAllOpportunities();

    @GetMapping("/opp/get/{id}")
    OpportunityDTO findById(@PathVariable Long id);

}
