package com.ironhack.salesrepservice.proxy;

import com.ironhack.salesrepservice.dto.SalesRepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("report-service")
public interface ReportServiceProxy {

    @PostMapping("/api/v1/post/salesreps/add")
    SalesRepDTO addOrUpdateSalesRep(@RequestBody SalesRepDTO salesrepDTO);

    @PostMapping("/post/salesreps")
    String createSalesrepDatabase(List<SalesRepDTO> salesrepDTOList);
}

