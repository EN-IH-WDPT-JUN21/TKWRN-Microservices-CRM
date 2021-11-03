package com.ironhack.stolen_name_trucking_company_homework_3.config;

import com.ironhack.stolen_name_trucking_company_homework_3.proxy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {


    @Autowired
    private AccountServiceProxy accountServiceProxy;

    @Autowired
    private LeadServiceProxy leadServiceProxy;

    @Autowired
    private OpportunityServiceProxy opportunityServiceProxy;

    @Autowired
    private ReportDBServiceProxy reportDBServiceProxy;

    @Autowired
    private SalesRepServiceProxy salesRepServiceProxy;

    @Autowired
    private ContactServiceProxy contactServiceProxy;


    @Bean
    AccountServiceProxy accountServiceProxy(){
        return new AccountServiceProxy();
    }
}
