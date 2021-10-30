package com.ironhack.salesrepservice.proxy;

import com.ironhack.salesrepservice.dao.SalesRep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("report-service")
public interface ReportServiceProxy {

    @PostMapping("/post/salesreps/add")
    SalesRep addSalesRep(@RequestBody SalesRep salesrep);
}

