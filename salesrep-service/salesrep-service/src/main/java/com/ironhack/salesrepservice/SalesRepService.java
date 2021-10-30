package com.ironhack.salesrepservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SalesRepService {

    @Autowired
    SalesRepRepository salesRepRepository;

    public SalesRepDTO getById(long id) {
        SalesRep salesRep = salesRepRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SalesRep with id " + id + " does not exist."));
        SalesRepDTO salesRepDTO = convertSalesToDto(salesRep);
        return salesRepDTO;
    }


    public SalesRepDTO create(SalesRepDTO salesRepDTO) {
        Optional<SalesRep> sales = salesRepRepository.findById(salesRepDTO.getId());
        if(sales.isEmpty()) {
            SalesRep newSales = convertDtoToSales(salesRepDTO);
            salesRepRepository.save(newSales);
            return convertSalesToDto(newSales);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The lead id already exists in the system.");
        }
    }

    public SalesRepDTO convertSalesToDto(SalesRep salesRep) {
        SalesRepDTO newSales = new SalesRepDTO(salesRep.getId(), salesRep.getRepName());
        return newSales;
    }

    public SalesRep convertDtoToSales(SalesRepDTO salesRepDTO) {
        SalesRep newSales = new SalesRep(salesRepDTO.getId(), salesRepDTO.getRepName());
        return newSales;
    }
}
