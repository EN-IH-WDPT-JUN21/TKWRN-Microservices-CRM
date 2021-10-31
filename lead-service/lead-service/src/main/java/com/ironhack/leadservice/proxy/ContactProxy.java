package com.ironhack.leadservice.proxy;

import com.ironhack.contactservice.dto.ContactDTO;
import com.ironhack.leadservice.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("contact-service")
public interface ContactProxy {

    @PostMapping("/api/v1/contacts")
    ContactDTO createContact(LeadDTO contactDTO);
}
