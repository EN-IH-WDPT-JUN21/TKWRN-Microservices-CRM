package com.ironhack.salesrepservice.proxy;

import com.ironhack.salesrepservice.dao.SalesRep;
import com.ironhack.salesrepservice.dto.SalesRepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("report-service")
public interface ReportServiceProxy {

    @PostMapping("/api/v1/report-db/new/salesrep")
    SalesRep addOrUpdateSalesRep(@RequestBody SalesRep salesrep);

    @PostMapping("/api/v1/report-db/post/salesreps")
    String createSalesrepDatabase(@RequestBody List<SalesRep> salesrepList);
}

