package com.ironhack.ReportService.Controller;

import com.ironhack.ReportService.Service.QueryHelperService;
import com.ironhack.ReportService.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/opportunity-report")
public class OpportunityReportController {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    QueryHelperService queryHelperService;

    @GetMapping("/count-by-rep")
    List<Object[]> findCountOpportunityByRepName(){
        return opportunityRepository.findCountOpportunityByRepName();
    }

    @GetMapping("/count-by-rep/{status}")
    List<Object[]> findCountOpportunityByRepNameForStatus(@PathVariable String status){
        return opportunityRepository.findCountOpportunityByRepNameForStatus(status);
    }

    @GetMapping("/count-by-product")
    List<Object[]> findCountOppForProduct(){
        return opportunityRepository.findCountOppForProduct();
    }

    @GetMapping("/count-by-product/{status}")
    List<Object[]> findCountOpportunityByProductForStatus(@PathVariable String status){
        return opportunityRepository.findCountOpportunityByProductForStatus(status);
    }

    @GetMapping("/count-by-country")
    List<Object[]> findCountOppForCountry(){
        return opportunityRepository.findCountOppForCountry();
    }

    @GetMapping("/count-by-country/{status}")
    List<Object[]> findCountOpportunityByCountryForStatus(@PathVariable String status){
        return opportunityRepository.findCountOpportunityByCountryForStatus(status);
    }

    @GetMapping("/count-by-city")
    List<Object[]> findCountOppForCity(){
        return opportunityRepository.findCountOppForCity();
    }

    @GetMapping("/count-by-city/{status}")
    List<Object[]> findCountOpportunityByCityForStatus(@PathVariable  String status){
        return opportunityRepository.findCountOpportunityByCityForStatus(status);
    }

    @GetMapping("/count-by-industry")
    List<Object[]> findCountOppForIndustry(){
        return opportunityRepository.findCountOppForIndustry();
    }

    @GetMapping("/count-by-industry/{status}")
    List<Object[]> findCountOpportunityByIndustryForStatus(@PathVariable String status){
        return opportunityRepository.findCountOpportunityByIndustryForStatus(status);
    }

    @GetMapping("/mean-product-quantity")
    Optional<Double> findMeanProductQuantity(){
        return opportunityRepository.findMeanProductQuantity();
    }

    @GetMapping("/median-product-quantity")
    Double findMedianQuantityStep1() {
        return queryHelperService.findMedianQuantity();
    }

    @GetMapping("/max-product-quantity")
    Optional<Integer> findMaxProductQuantity(){
        return opportunityRepository.findMaxProductQuantity();
    }

    @GetMapping("/min-product-quantity")
    Optional<Integer> findMinProductQuantity(){
        return opportunityRepository.findMinProductQuantity();
    }

    @GetMapping("mean-opportunities-per-account")
    Optional<Double>findMeanOpportunitiesPerAccount(){
        return opportunityRepository.findMeanOpportunitiesPerAccount();
    }

    @GetMapping("/median-opportunities-per-account")
    Double findMedianOppsPerAccountStep1() {
        return queryHelperService.findMedianOppsPerAccount();
    }

    @GetMapping("max-opportunities-per-account")
    Optional<Double>findMaxOpportunitiesPerAccount(){
        return opportunityRepository.findMaxOpportunitiesPerAccount();
    }

    @GetMapping("min-opportunities-per-account")
    Optional<Double>findMinOpportunitiesPerAccount(){
        return opportunityRepository.findMinOpportunitiesPerAccount();
    }
}
