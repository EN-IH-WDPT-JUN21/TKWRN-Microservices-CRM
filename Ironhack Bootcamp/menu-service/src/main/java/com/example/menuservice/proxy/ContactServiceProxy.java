package com.example.menuservice.proxy;


import com.example.menuservice.dto.ContactReceiptDTO;
import com.example.menuservice.dto.ContactRequestDTO;
import com.example.menuservice.dto.LeadRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("contact-service")
@RequestMapping({"/api/v1/contacts"})
public interface ContactServiceProxy {


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactReceiptDTO> getContacts();

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ContactReceiptDTO getContactById(@PathVariable("id") long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactReceiptDTO createContact(@RequestBody LeadRequestDTO leadDTO);

    @PatchMapping({"/change-account/{id}"})
    public void updateContact(@PathVariable("id") long id, @RequestBody ContactRequestDTO contactDTO);
}
