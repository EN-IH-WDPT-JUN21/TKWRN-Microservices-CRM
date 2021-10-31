package com.ironhack.contactservice.sampleData;

import com.ironhack.contactservice.repository.ContactRepository;
import com.ironhack.contactservice.dao.Contact;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private ContactRepository contactRepository;

    public SampleDataLoader(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Contact> contacts = contactRepository.saveAll(List.of(
                new Contact("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", 1L),
                new Contact("Martha Steward", "123475357", "ms@wp.pl", "My own company", 2L),
                new Contact("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show", 2L)

        ));
    }
}
