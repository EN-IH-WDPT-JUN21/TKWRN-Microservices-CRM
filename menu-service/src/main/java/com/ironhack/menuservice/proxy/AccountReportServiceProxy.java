package com.ironhack.menuservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@FeignClient("report-service")
public interface AccountReportServiceProxy {

    @GetMapping("/api/v1/account-report/mean-employee-count")
    Optional<Double> findMeanEmployeeCount();

    @GetMapping("/api/v1/account-report/median-employee-count")
    int[]findMedianEmployeeCountStep1();

    @GetMapping("/api/v1/account-report/max-employee-count")
    Optional<Integer> findMaxEmployeeCount();

    @GetMapping("/api/v1/account-report/min-employee-count")
    Optional<Integer> findMinEmployeeCount();
}
