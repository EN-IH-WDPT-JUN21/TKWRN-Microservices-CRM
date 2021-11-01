package com.ironhack.OpportunityService.sample_data;

import com.github.javafaker.Faker;
import com.ironhack.OpportunityService.dao.Opportunity;
import com.ironhack.OpportunityService.enums.Status;
import com.ironhack.OpportunityService.enums.Truck;
import com.ironhack.OpportunityService.repository.OpportunityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final OpportunityRepository opportunityRepository;
    private final Faker faker;

    public SampleDataLoader(OpportunityRepository opportunityRepository, Faker faker) {
        this.opportunityRepository = opportunityRepository;
        this.faker = faker;
    }

    @Override
    public void run(String... args){

        List<Opportunity> sampleOpps = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new Opportunity(
                    (long) i,
                    Status.OPEN,
                    Truck.HYBRID,
                    faker.number().numberBetween(1, 100),
                    faker.number().numberBetween(1L, 100L),
                    faker.number().numberBetween(1L, 100L),
                    faker.number().numberBetween(1L, 100L)
                )).collect(Collectors.toList());

        opportunityRepository.saveAll(sampleOpps);

    }

}
