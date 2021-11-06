package com.ironhack.menuservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@FeignClient("report-service")
@RequestMapping("/api/v1/account-report")
public interface AccountReportServiceProxy {

    @GetMapping("/mean-employee-count")
    Optional<Double> findMeanEmployeeCount();

    @GetMapping("/median-employee-count")
    Double findMedianEmployeeCountStep1();

    @GetMapping("/max-employee-count")
    Optional<Integer> findMaxEmployeeCount();

    @GetMapping("/min-employee-count")
    Optional<Integer> findMinEmployeeCount();
}
