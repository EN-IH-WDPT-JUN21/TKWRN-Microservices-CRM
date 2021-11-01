package com.ironhack.salesrepservice.sample_data;

import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Override
    public void run(String... args) {

        salesRepRepository.saveAll(List.of(
                new SalesRep("Tom Jones"),
                new SalesRep("John Wick"),
                new SalesRep("Tom Brady")
        ));
    }
}
