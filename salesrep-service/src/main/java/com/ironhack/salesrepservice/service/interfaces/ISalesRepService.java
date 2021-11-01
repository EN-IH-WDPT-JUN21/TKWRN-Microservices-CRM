package com.ironhack.salesrepservice.service.interfaces;

import com.ironhack.salesrepservice.dto.SalesRepDTO;

import java.util.List;

public interface ISalesRepService {

    SalesRepDTO addSalesRep(SalesRepDTO salesRepDTO);
    SalesRepDTO findById(Long id);
    List<SalesRepDTO> findAllSalesReps();
    SalesRepDTO updateSalesRepName(Long id, String name);
    void deleteSalesRep(Long id);
}
