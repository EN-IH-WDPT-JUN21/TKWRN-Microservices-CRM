package com.ironhack.accountservice.sampleData;

import com.ironhack.accountservice.dao.Account;
import com.ironhack.accountservice.enums.Industry;
import com.ironhack.accountservice.proxy.ReportServiceProxy;
import com.ironhack.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {
    private AccountRepository accountRepository;

    @Autowired
    ReportServiceProxy reportServiceProxy;

    public SampleDataLoader(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void run(String... args) throws Exception {
        this.accountRepository.saveAll(List.of(new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM"),
                new Account(Industry.ECOMMERCE, 500, "Madrid", "SPAIN"),
                new Account(Industry.MANUFACTURING, 20, "Paris", "FRANCE")));

        reportServiceProxy.createAccountDatabase(List.of(new Account(1L, Industry.PRODUCE, 50, "London", "UNITED KINGDOM"),
                new Account(2L, Industry.ECOMMERCE, 500, "Madrid", "SPAIN"),
                new Account(3L, Industry.MANUFACTURING, 20, "Paris", "FRANCE")));
    }
}
