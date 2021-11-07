package com.ironhack.contactservice.controller;

import com.ironhack.contactservice.dao.Contact;
import com.ironhack.contactservice.dto.ContactDTO;
import com.ironhack.contactservice.dto.LeadDTO;
import com.ironhack.contactservice.dto.UpdatedContactDTO;
import com.ironhack.contactservice.repository.ContactRepository;
import com.ironhack.contactservice.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private ContactRepository contactRepository;

    private ContactService contactService;

    public ContactController(ContactRepository contactRepository, ContactService contactService) {
        this.contactRepository = contactRepository;
        this.contactService = contactService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO getContactById(@PathVariable("id") long id) {
        return contactService.getById(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody LeadDTO leadDTO) {
        return contactService.create(leadDTO);
    }

    @PutMapping("/change-account/{id}")
    public void updateContact(@PathVariable("id") long id, @RequestBody ContactDTO contactDTO) {
        contactService.update(id, contactDTO);
    }

    @GetMapping("/get-by-account/{id}")
    public List<Contact> getAllContactsByAccount(@PathVariable Long id){
        return contactService.findAllByAccountId(id);
    }
}
