package com.ironhack.salesrepservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Override
    public void run(String... args) throws Exception {
        List<SalesRepDTO> salesReps = new ArrayList<>();
        salesReps.add(new SalesRepDTO(1L, "Mary Jane"));
        salesReps.add(new SalesRepDTO(2L, "Joe Doe"));

        salesRepRepository.saveAll(List.of(
                new SalesRep("Chris Watts"),
                new SalesRep("Bonnie Fax")
        ));
    }
}
