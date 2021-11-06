package com.ironhack.ReportService.Controller;

import com.ironhack.ReportService.Service.QueryHelperService;
import com.ironhack.ReportService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountReportController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    QueryHelperService queryHelperService;

    @GetMapping("/api/v1/account-report/mean-employee-count")
    Optional<Double> findMeanEmployeeCount(){
        return accountRepository.findMeanEmployeeCount();
    }

    @GetMapping("/api/v1/account-report/median-employee-count")
    Double findMedianEmployeeCountStep1(){
        return queryHelperService.findMedianEmployeeCount();
    }

    @GetMapping("/api/v1/account-report/max-employee-count")
    Optional<Integer> findMaxEmployeeCount(){
        return accountRepository.findMaxEmployeeCount();
    }

    @GetMapping("/api/v1/account-report/min-employee-count")
    Optional<Integer> findMinEmployeeCount(){
        return accountRepository.findMinEmployeeCount();
    }
}
