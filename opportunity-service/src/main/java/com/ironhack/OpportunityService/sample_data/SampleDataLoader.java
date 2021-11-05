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


        List<Opportunity>  opportunities = opportunityRepository.saveAll(List.of(
                new Opportunity(1l, Status.OPEN, Truck.FLATBED, 10, 1l, 1l, 1l),
                new Opportunity(2l, Status.OPEN, Truck.BOX, 1150, 2l, 2l, 1l),
                new Opportunity(3l, Status.OPEN, Truck.HYBRID, 1, 3l, 3l, 2l)

        ));

        opportunityRepository.saveAll(opportunities);

    }

}
