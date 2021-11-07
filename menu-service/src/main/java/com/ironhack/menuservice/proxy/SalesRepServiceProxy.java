package com.ironhack.menuservice.proxy;

import com.ironhack.menuservice.dto.SalesRepReceiptDTO;
import com.ironhack.menuservice.dto.SalesRepRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("salesrep-service")
public interface SalesRepServiceProxy {

    @PostMapping("/api/v1/sales-reps/new")
    @ResponseStatus(HttpStatus.CREATED)
    SalesRepReceiptDTO addSalesRep(@RequestBody SalesRepRequestDTO salesRepDTO);

    @GetMapping("/api/v1/sales-reps")
    @ResponseStatus(HttpStatus.OK)
    List<SalesRepRequestDTO> getAllSalesReps();

    @GetMapping("/api/v1/sales-reps/{id}")
    @ResponseStatus(HttpStatus.OK)
    SalesRepRequestDTO findById(@PathVariable Long id);

    @PutMapping("/api/v1/sales-reps/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    SalesRepRequestDTO updateSalesRepName(@PathVariable Long id, @RequestParam String name);

    @DeleteMapping("/api/v1/sales-reps/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteSalesRep(@PathVariable Long id);

}
