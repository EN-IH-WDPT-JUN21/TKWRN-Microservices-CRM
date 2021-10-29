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
    @GetMapping("/contacts/{id}")
    public ContactReceiptDTO findContactById (@PathVariable(name="id") Long id);

    @PatchMapping("/contacts/change-account/{id}")
    void updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid ContactUpdateDTO contactUpdateDTO);
}
