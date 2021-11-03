package com.ironhack.stolen_name_trucking_company_homework_3.proxy;

import com.ironhack.stolen_name_trucking_company_homework_3.dto.OpportunityReceiptDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.OpportunityRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("opportunity-service")
@RequestMapping("/api/v1/opps")
public interface OpportunityServiceProxy {

    @GetMapping("/get")
    public List<OpportunityReceiptDTO> getAll();

    @GetMapping("/get/{id}")
    public OpportunityReceiptDTO getById(@PathVariable Long id);

    @GetMapping("/get-by-account/{id}")
    public List<OpportunityRequestDTO> getAllOppsByAccount(@PathVariable Long id);

    @PostMapping("/new")
    public OpportunityReceiptDTO createOpportunity(@RequestBody OpportunityRequestDTO opportunityDTO);

    @PutMapping("/update/{id}")
    public OpportunityReceiptDTO updateOpportunity(@PathVariable Long id, @RequestBody OpportunityRequestDTO opportunityDTO);

    @PutMapping("change-account/{id}")
    void updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid OpportunityRequestDTO opportunityDTO);

    @DeleteMapping("/delete/{id}")
    public void deleteOpportunity(@PathVariable Long id);

}
