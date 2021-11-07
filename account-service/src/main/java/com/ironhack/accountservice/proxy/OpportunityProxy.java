package com.ironhack.accountservice.proxy;

import com.ironhack.accountservice.controller.dto.OpportunityReceiptDTO;
import com.ironhack.accountservice.controller.dto.OpportunityUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("opportunity-service")
public interface OpportunityProxy {
    @GetMapping("/api/v1/opps/get/{id}")
    OpportunityReceiptDTO getById(@PathVariable(name = "id") Long id);

    @PutMapping("/api/v1/opps/update/{id}")
    void updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid OpportunityUpdateDTO opportunityUpdateDTO);

    @GetMapping("/api/v1/opps/get-by-account/{id}")
    List<OpportunityReceiptDTO> getAllOppsByAccount(@PathVariable Long id);
}
