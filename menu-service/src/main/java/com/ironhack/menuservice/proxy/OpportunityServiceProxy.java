package com.ironhack.menuservice.proxy;

import com.ironhack.menuservice.dto.OpportunityReceiptDTO;
import com.ironhack.menuservice.dto.OpportunityRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("opportunity-service")
public interface OpportunityServiceProxy {

    @GetMapping("/api/v1/opps/get")
    public List<OpportunityReceiptDTO> getAll();

    @GetMapping("/api/v1/opps/get/{id}")
    public OpportunityReceiptDTO getById(@PathVariable Long id);

    @GetMapping("/api/v1/opps/get-by-account/{id}")
    public List<OpportunityRequestDTO> getAllOppsByAccount(@PathVariable Long id);

    @PostMapping("/api/v1/opps/new")
    public OpportunityReceiptDTO createOpportunity(@RequestBody OpportunityRequestDTO opportunityDTO);

    @PutMapping("/api/v1/opps/update/{id}")
    public OpportunityReceiptDTO updateOpportunity(@PathVariable Long id, @RequestBody OpportunityReceiptDTO opportunityDTO);

    @PutMapping("/api/v1/opps/change-account/{id}")
    void updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid OpportunityRequestDTO opportunityDTO);

    @DeleteMapping("/api/v1/opps/delete/{id}")
    public void deleteOpportunity(@PathVariable Long id);

}
