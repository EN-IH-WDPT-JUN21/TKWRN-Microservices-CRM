package com.ironhack.leadservice.proxies;

import com.ironhack.leadservice.dto.SalesRepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("salesrep-service")
public interface SalesProxy {

    @GetMapping("/api/v1/sales-reps/{id}")
    SalesRepDTO getSales(@PathVariable(value = "id") long id);
}
