package com.ironhack.salesrepservice.proxy;

import com.ironhack.salesrepservice.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("lead-service")
public interface LeadServiceProxy {

    @GetMapping("/leads")
    List<LeadDTO> getAllLeads();

    @GetMapping("/leads/{id}")
    LeadDTO findById(@PathVariable Long id);

}
