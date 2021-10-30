package com.ironhack.salesrepservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SalesrepServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesrepServiceApplication.class, args);
	}

}
