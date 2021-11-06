package com.ironhack.contactservice.sampleData;

import com.ironhack.contactservice.dao.Contact;
import com.ironhack.contactservice.proxies.ReportServiceProxy;
import com.ironhack.contactservice.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {
    @Autowired
    ReportServiceProxy reportServiceProxy;

    private ContactRepository contactRepository;

    public SampleDataLoader(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void run(String... args) throws Exception {
        this.contactRepository.saveAll(List.of(new Contact("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", 1L),
                new Contact("Martha Steward", "123475357", "ms@wp.pl", "My own company", 2L),
                new Contact("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show", 2L)));

        reportServiceProxy.createContactDatabase(List.of(new Contact(1L,  "John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", 1L, 1L),
                new Contact(2L,"Martha Steward", "123475357", "ms@wp.pl", "My own company", 2L, 2L),
                new Contact(3l, "George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show", 2L, 2L)));
    }
}
