package com.ironhack.contactservice.controller;

import com.ironhack.contactservice.repository.ContactRepository;
import com.ironhack.contactservice.service.ContactService;
import com.ironhack.contactservice.dao.Contact;
import com.ironhack.contactservice.dto.ContactDTO;
import com.ironhack.contactservice.dto.LeadDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private ContactRepository contactRepository;

    private ContactService contactService;

    public ContactController(ContactRepository contactRepository,
                             ContactService contactService) {
        this.contactRepository = contactRepository;
        this.contactService = contactService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO getContactById(@PathVariable(value = "id") long id) {
        return contactService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody LeadDTO leadDTO) {
        return contactService.create(leadDTO);
    }


    @PatchMapping("/change-account/{id}")
    public void updateContact(@PathVariable(value = "id") long id,
                                    @RequestParam(value = "accountId") ContactDTO contactDTO) {
       contactService.update(id, contactDTO);
    }
}
