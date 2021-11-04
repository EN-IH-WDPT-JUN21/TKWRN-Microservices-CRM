package com.example.menuservice.proxy;



import com.example.menuservice.dto.SalesRepRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("salesrep-service")
public interface SalesRepServiceProxy {
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepRequestDTO addSalesRep(@RequestBody SalesRepRequestDTO salesRepDTO);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRepRequestDTO> getAllSalesReps();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRepRequestDTO findById(@PathVariable Long id);

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRepRequestDTO updateSalesRepName(@PathVariable Long id, @RequestParam String name);

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSalesRep(@PathVariable Long id);

}
