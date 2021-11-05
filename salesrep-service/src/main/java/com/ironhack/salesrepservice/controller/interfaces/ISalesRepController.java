package com.ironhack.salesrepservice.controller.interfaces;

import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.SalesRepDTO;

public interface ISalesRepController {

    SalesRepDTO addSalesRep(SalesRepDTO salesRepDTO);
}
