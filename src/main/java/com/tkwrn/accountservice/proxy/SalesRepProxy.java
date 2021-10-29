package com.tkwrn.accountservice.proxy;

import com.tkwrn.accountservice.controller.dto.SalesRepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient("sales-rep-service")
public interface SalesRepProxy {
    @GetMapping("/sales-reps/{id}")
    public SalesRepDTO findSalesRepById (@PathVariable(name="id") Long id);
}
