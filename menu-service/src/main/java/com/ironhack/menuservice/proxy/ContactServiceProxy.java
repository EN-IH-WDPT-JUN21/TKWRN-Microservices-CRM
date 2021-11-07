package com.ironhack.menuservice.proxy;

import com.ironhack.menuservice.dto.ContactReceiptDTO;
import com.ironhack.menuservice.dto.ContactRequestDTO;
import com.ironhack.menuservice.dto.LeadRequestDTO;
import com.ironhack.menuservice.dto.UpdatedContactDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("contact-service")
public interface ContactServiceProxy {

    @GetMapping("/api/v1/contacts")
    @ResponseStatus(HttpStatus.OK)
    public List<ContactReceiptDTO> getContacts();

    @GetMapping("/api/v1/contacts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactReceiptDTO getContactById(@PathVariable("id") long id);

    @PostMapping("/api/v1/contacts/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactReceiptDTO createContact(@RequestBody LeadRequestDTO leadDTO);

    @PatchMapping({"(/api/v1/contacts/change-account/{id}"})
    public void updateContact(@PathVariable("id") long id, @RequestBody UpdatedContactDTO contactDTO);
}
