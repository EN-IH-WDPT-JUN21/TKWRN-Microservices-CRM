package com.ironhack.salesrepservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalesRepController {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    SalesRepService salesRepService;

    @GetMapping("/sales")
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRep> getAllSales() {
        return salesRepRepository.findAll();
    }

    @GetMapping("/sales/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRepDTO getSalesById(@PathVariable(value = "id") long id) {
        return salesRepService.getById(id);
    }

    @PostMapping("/sales")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDTO createSales(@RequestBody SalesRepDTO salesRepDTO) {

        return salesRepService.create(salesRepDTO);
    }
}
