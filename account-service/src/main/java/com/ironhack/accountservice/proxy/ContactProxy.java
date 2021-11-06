package com.ironhack.accountservice.proxy;

import com.ironhack.accountservice.controller.dto.ContactReceiptDTO;
import com.ironhack.accountservice.controller.dto.ContactUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Service
@FeignClient("contact-service")
public interface ContactProxy {
    @GetMapping("/api/v1/contacts/{id}")
    ContactReceiptDTO getContactById (@PathVariable(name="id") Long id);

    @PutMapping("/api/v1/contacts/change-account/{id}")
    void updateContact(@PathVariable(name = "id") Long id, @RequestBody @Valid ContactReceiptDTO contactUpdateDTO);

    @GetMapping("/api/v1/contacts/get-by-account/{id}")
    List<ContactReceiptDTO> getAllContactsByAccount(@PathVariable Long id);
}
