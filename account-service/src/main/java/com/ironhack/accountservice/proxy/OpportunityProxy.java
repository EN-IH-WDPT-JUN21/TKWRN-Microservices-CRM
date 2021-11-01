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
@FeignClient("opportunity-service")
public interface OpportunityProxy {
    @GetMapping("/api/v1/opps/get/{id}")
    OpportunityReceiptDTO getById(@PathVariable(name = "id") Long id);

    @PatchMapping("/api/v1/opps/change-account/{id}")
    void updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid OpportunityUpdateDTO opportunityUpdateDTO);
}
