package com.ironhack.leadservice.sampleData;

import com.ironhack.leadservice.dto.LeadDTO;
import com.ironhack.leadservice.proxies.ReportServiceProxy;
import com.ironhack.leadservice.repository.LeadRepository;
import com.ironhack.leadservice.dao.Lead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    ReportServiceProxy reportServiceProxy;

    @Override
    public void run(String... args) throws Exception {
        /*List<SalesRepDTO> salesReps = new ArrayList<>();
        salesReps.add(new SalesRepDTO(1L, "Mary Jane"));
        salesReps.add(new SalesRepDTO(2L, "Joe Doe"));*/

        leadRepository.saveAll(List.of(
                new Lead("Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", 1L),
                new Lead("Lee Dawson", "980651164", "ld@gmail.com", "LeeD", 1L),
                new Lead("Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland", 2L)
        ));

        reportServiceProxy.createLeadDatabase(List.of(
                new LeadDTO(1L, "Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", 1L),
                new LeadDTO(2L,"Lee Dawson", "980651164", "ld@gmail.com", "LeeD", 1L),
                new LeadDTO(3L,"Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland", 2L)
        ));

    }
}
