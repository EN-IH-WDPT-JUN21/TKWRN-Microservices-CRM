package com.ironhack.menuservice.proxy;

import com.ironhack.menuservice.dto.SalesRepRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("salesrep-service")
@RequestMapping("/api/v1/sales-reps")
public interface SalesRepServiceProxy {

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    SalesRepRequestDTO addSalesRep(@RequestBody SalesRepRequestDTO salesRepDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<SalesRepRequestDTO> getAllSalesReps();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    SalesRepRequestDTO findById(@PathVariable Long id);

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    SalesRepRequestDTO updateSalesRepName(@PathVariable Long id, @RequestParam String name);

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteSalesRep(@PathVariable Long id);

}
