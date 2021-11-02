package com.ironhack.salesrepservice.controller.impl;

import com.ironhack.salesrepservice.controller.interfaces.ISalesRepController;
import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.SalesRepDTO;
import com.ironhack.salesrepservice.repository.SalesRepRepository;
import com.ironhack.salesrepservice.service.interfaces.ISalesRepService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sales-reps")
public class SalesRepController implements ISalesRepController {

    SalesRepRepository salesRepRepository;
    ISalesRepService salesRepService;


    public SalesRepController(SalesRepRepository salesRepRepository, ISalesRepService salesRepService) {
        this.salesRepRepository = salesRepRepository;
        this.salesRepService = salesRepService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDTO addSalesRep(@RequestBody SalesRepDTO salesRepDTO) {
        return salesRepService.addSalesRep(salesRepDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRepDTO> getAllSalesReps() {
        return salesRepService.findAllSalesReps();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRepDTO findById(@PathVariable Long id) {
        return salesRepService.findById(id);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRepDTO updateSalesRepName(@PathVariable Long id, @RequestParam String name) {
        return salesRepService.updateSalesRepName(id, name);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSalesRep(@PathVariable Long id) {
        salesRepService.deleteSalesRep(id);
    }

    @PostMapping("/populate")
    @ResponseStatus(HttpStatus.OK)
    public String createSalesrepDatabase() {
        return salesRepService.populateSalesrepDatabase();
    }
}
