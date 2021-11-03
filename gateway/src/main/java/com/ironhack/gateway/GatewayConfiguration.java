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

                // Account Service
                .route(p -> p.path("/api/v1/accounts/**")
                        .uri("lb://ACCOUNT-SERVICE"))
                .route(p -> p.path("/api/v1/accounts**")
                        .uri("lb://ACCOUNT-SERVICE"))

                // Contact Service
                .route(p -> p.path("/api/v1/contacts/**")
                        .uri("lb://CONTACT-SERVICE"))
                .route(p -> p.path("/api/v1/contacts**")
                        .uri("lb://CONTACT-SERVICE"))

                // Lead service
                .route(p -> p.path("/leads/**")
                        .uri("lb://LEAD-SERVICE"))
                .route(p -> p.path("/leads**")
                        .uri("lb://LEAD-SERVICE"))

                // Opportunity service
                .route(p -> p.path("/api/v1/opps/**")
                        .uri("lb://OPPORTUNITY-SERVICE"))
                .route(p -> p.path("/api/v1/opps**")
                        .uri("lb://OPPORTUNITY-SERVICE"))

                // Report service
                .route(p -> p.path("/api/v1/report-db**")
                        .uri("lb://REPORT-SERVICE"))
                .route(p -> p.path("/api/v1/report-db/**")
                        .uri("lb://REPORT-SERVICE"))
                .route(p -> p.path("/api/v1/account-report**")
                        .uri("lb://REPORT-SERVICE"))
                .route(p -> p.path("/api/v1/account-report/**")
                        .uri("lb://REPORT-SERVICE"))
                .route(p -> p.path("/api/v1/opportunity-report**")
                        .uri("lb://REPORT-SERVICE"))
                .route(p -> p.path("/api/v1/opportunity-report/**")
                        .uri("lb://REPORT-SERVICE"))

                // Salesrep service
                .route(p -> p.path("/api/v1/sales-reps/**")
                        .uri("lb://SALESREP-SERVICE"))
                .route(p -> p.path("/api/v1/sales-reps**")
                        .uri("lb://SALESREP-SERVICE"))

                .build();
    }
}
