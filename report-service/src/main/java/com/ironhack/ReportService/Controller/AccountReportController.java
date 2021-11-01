package com.ironhack.ReportService.Controller;

import com.ironhack.ReportService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account-report")
public class AccountReportController {

    @Autowired
    AccountRepository accountRepository;

    /*@GetMapping("/mean-employee-count")
    Optional<Double> findMeanEmployeeCount(){
        return accountRepository.findMeanEmployeeCount();
    }*/

//    @GetMapping("/median-employee-count")
//    int[]findMedianEmployeeCountStep1(){
//        return accountRepository.findMedianEmployeeCountStep1();
//    }

    /*@GetMapping("/max-employee-count")
    Optional<Integer> findMaxEmployeeCount(){
        return accountRepository.findMaxEmployeeCount();
    }

    @GetMapping("/min-employee-count")
    Optional<Integer> findMinEmployeeCount(){
        return accountRepository.findMinEmployeeCount();
    }*/
}
