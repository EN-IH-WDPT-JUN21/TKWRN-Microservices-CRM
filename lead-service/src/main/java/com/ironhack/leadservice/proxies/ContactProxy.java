package com.ironhack.leadservice.proxies;

import com.ironhack.leadservice.dto.ContactDTO;
import com.ironhack.leadservice.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("contact-service")
public interface ContactProxy {

    @PostMapping("/api/v1/contacts")
    ContactDTO createContact(@RequestBody LeadDTO leadDTO);
}
