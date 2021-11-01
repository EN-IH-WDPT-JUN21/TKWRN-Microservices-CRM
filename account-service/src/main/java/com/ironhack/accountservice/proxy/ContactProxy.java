package com.ironhack.accountservice.proxy;

import com.ironhack.accountservice.controller.dto.ContactReceiptDTO;
import com.ironhack.accountservice.controller.dto.ContactUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@FeignClient("contact-service")
public interface ContactProxy {
    @GetMapping("/api/v1/contacts/{id}")
    ContactReceiptDTO getContactById (@PathVariable(name="id") Long id);

    @PatchMapping("/api/v1/contacts/change-account/{id}")
    void updateContact(@PathVariable(name = "id") Long id, @RequestBody @Valid ContactUpdateDTO contactUpdateDTO);
}
