package com.ironhack.salesrepservice.sample_data;

import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.proxy.ReportServiceProxy;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    ReportServiceProxy reportServiceProxy;

    @Override
    public void run(String... args) {

        salesRepRepository.saveAll(List.of(
                new SalesRep("Tom Jones"),
                new SalesRep("John Wick"),
                new SalesRep("Tom Brady")
        ));

        reportServiceProxy.createSalesrepDatabase(List.of(
                new SalesRep(1L, "Tom Jones"),
                new SalesRep(2L, "John Wick"),
                new SalesRep(3L, "Tom Brady")));
    }
}
