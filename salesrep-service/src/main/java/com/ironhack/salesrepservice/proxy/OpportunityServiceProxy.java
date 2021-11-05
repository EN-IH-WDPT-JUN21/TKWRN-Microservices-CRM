package com.ironhack.salesrepservice.proxy;

import com.ironhack.salesrepservice.dto.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("opportunity-service")
public interface OpportunityServiceProxy {

    @GetMapping("/api/v1/opps/get")
    List<OpportunityDTO> getAllOpportunities();

    @GetMapping("/api/v1/opps/get/{id}")
    OpportunityDTO findById(@PathVariable Long id);

}
