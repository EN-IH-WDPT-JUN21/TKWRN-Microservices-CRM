package com.tkwrn.accountservice.proxy;

import com.tkwrn.accountservice.controller.dto.ContactReceiptDTO;
import com.tkwrn.accountservice.controller.dto.ContactRequestDTO;
import com.tkwrn.accountservice.controller.dto.ContactUpdateDTO;
import com.tkwrn.accountservice.controller.dto.OpportunityUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
@FeignClient("contact-service")
public interface ContactProxy {
    @GetMapping("/contacts/{id}")
    public ContactReceiptDTO findContactById (@PathVariable(name="id") Long id);

    @PatchMapping("/contacts/change-account/{id}")
    void updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid ContactUpdateDTO contactUpdateDTO);
}
