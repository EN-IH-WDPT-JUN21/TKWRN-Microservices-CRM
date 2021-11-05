package com.ironhack.contactservice.service;

import com.ironhack.contactservice.dao.Contact;
import com.ironhack.contactservice.dto.ContactDTO;
import com.ironhack.contactservice.dto.LeadDTO;
import com.ironhack.contactservice.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDTO getById(long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact with id " + id + " does not exist."));
        return convertContactToDto(contact);
    }

    public void update(long id, ContactDTO updatedDTO) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact with id " + id + " does not exist."));
        contact.setAccountId(updatedDTO.getAccountId());
        contactRepository.save(contact);
    }

    public ContactDTO create(LeadDTO leadDTO) {
        Contact newContact = new Contact(leadDTO.getName(),
                leadDTO.getPhoneNumber(),
                leadDTO.getEmail(),
                leadDTO.getCompanyName(),
                leadDTO.getSalesId());
        contactRepository.save(newContact);
        return convertContactToDto(newContact);
    }

    public ContactDTO convertContactToDto(Contact contact) {
        return new ContactDTO(contact.getId(),
                              contact.getName(),
                              contact.getPhoneNumber(),
                              contact.getEmail(),
                              contact.getCompanyName(),
                              contact.getSalesId());
    }

    public Contact convertDtoToContact(ContactDTO contactDTO) {
        return new Contact(contactDTO.getId(),
                           contactDTO.getName(),
                           contactDTO.getPhoneNumber(),
                           contactDTO.getEmail(),
                           contactDTO.getCompanyName(),
                           contactDTO.getSalesId(),
                           contactDTO.getAccountId());

    }
}
