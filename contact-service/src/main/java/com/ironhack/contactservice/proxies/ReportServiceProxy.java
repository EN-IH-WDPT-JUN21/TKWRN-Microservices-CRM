package com.ironhack.contactservice.proxies;

import com.ironhack.contactservice.dao.Contact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("report-service")
public interface ReportServiceProxy {

    @PostMapping("/api/v1/report-db/post/opportunities")
    String createContactDatabase(@RequestBody List<Contact> contactList);

    @PostMapping("/api/v1/report-db/new/opportunity")
    Contact addOrUpdateContact(@RequestBody Contact contact);
}
