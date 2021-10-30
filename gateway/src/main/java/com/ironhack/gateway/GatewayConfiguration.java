package com.ironhack.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()

                // Lead service
                .route(p -> p.path("/leads/**")
                        .uri("lb://LEAD-SERVICE"))

                // Opportunity service
                .route(p -> p.path("/opp/**")
                        .uri("lb://OPPORTUNITY-SERVICE"))

                // Account Service
                .route(p -> p.path("/accounts/**")
                        .uri("lb://ACCOUNT-SERVICE"))

                // Salesrep service
                .route(p -> p.path("/api/v1/sales-reps/**")
                        .uri("lb://SALESREP-SERVICE"))

                .build();
    }
}
