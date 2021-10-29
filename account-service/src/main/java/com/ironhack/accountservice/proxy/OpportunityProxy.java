package com.ironhack.accountservice.proxy;

import com.ironhack.accountservice.controller.dto.OpportunityReceiptDTO;
import com.ironhack.accountservice.controller.dto.OpportunityUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@FeignClient("opp-service")
public interface OpportunityProxy {
    @GetMapping("/oops/{id}")
    OpportunityReceiptDTO findOpportunityById(@PathVariable(name = "id") Long id);

    @PatchMapping("/oops/change-account/{id}")
    void updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid OpportunityUpdateDTO opportunityUpdateDTO);
}
