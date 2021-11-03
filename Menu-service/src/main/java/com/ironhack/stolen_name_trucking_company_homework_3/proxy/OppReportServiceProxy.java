package com.ironhack.stolen_name_trucking_company_homework_3.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@FeignClient("report-service")
@RequestMapping("/api/v1/opportunity-report")
public interface OppReportServiceProxy {

    @GetMapping("/count-by-rep")
    List<Object[]> findCountOpportunityByRepName();

    @GetMapping("/count-by-rep/{status}")
    List<Object[]> findCountOpportunityByRepNameForStatus(@PathVariable String status);

    @GetMapping("/count-by-product")
    List<Object[]> findCountOppForProduct();

    @GetMapping("/count-by-product/{status}")
    List<Object[]> findCountOpportunityByProductForStatus(@PathVariable Enum status);

    @GetMapping("/count-by-country")
    List<Object[]> findCountOppForCountry();

    @GetMapping("/count-by-country/{status}")
    List<Object[]> findCountOpportunityByCountryForStatus(@PathVariable String status);

    @GetMapping("/count-by-city")
    List<Object[]> findCountOppForCity();

    @GetMapping("/count-by-city/{status}")
    List<Object[]> findCountOpportunityByCityForStatus(@PathVariable  String status);

    @GetMapping("/count-by-industry")
    List<Object[]> findCountOppForIndustry();

    @GetMapping("/count-by-industry/{status}")
    List<Object[]> findCountOpportunityByIndustryForStatus(@PathVariable String status);

    @GetMapping("/mean-product-quantity")
    Optional<Double> findMeanProductQuantity();

//    // *** Median Report is needed JPQL can give list of all quantities in an ordered int array, needs a second step to find the median from this ***
//    @Query("SELECT quantity FROM Opportunity order by quantity")
//    int[]findMedianQuantityStep1();

    @GetMapping("/max-product-quantity")
    Optional<Integer> findMaxProductQuantity();

    @GetMapping("/min-product-quantity")
    Optional<Integer> findMinProductQuantity();

//    @GetMapping("mean-opportunities-per-account")
//    Optional<Double>findMeanOpportunitiesPerAccount(){
//        return opportunityRepository.findMeanOpportunitiesPerAccount();
//    }
//
////    // *** Median Report is needed JPQL can give list of all opportunitycounts in an ordered int array, needs a second step to find the median from this ***
////    @Query(value = "select count(distinct id) as count_opportunity from opportunity group by account_id order by count_opportunity", nativeQuery = true)
////    int[]findMedianOppsPerAccountStep1();
//
//    @GetMapping("max-opportunities-per-account")
//    Optional<Double>findMaxOpportunitiesPerAccount(){
//        return opportunityRepository.findMaxOpportunitiesPerAccount();
//    }
//
//    @GetMapping("min-opportunities-per-account")
//    Optional<Double>findMinOpportunitiesPerAccount(){
//        return opportunityRepository.findMinOpportunitiesPerAccount();
//    }
}
