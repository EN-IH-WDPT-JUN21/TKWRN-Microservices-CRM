package com.example.menuservice.proxy;

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

    @GetMapping("/max-product-quantity")
    Optional<Integer> findMaxProductQuantity();

    @GetMapping("/min-product-quantity")
    Optional<Integer> findMinProductQuantity();

    @GetMapping("mean-opportunities-per-account")
    Optional<Double>findMeanOpportunitiesPerAccount();

    @GetMapping("max-opportunities-per-account")
    Optional<Double>findMaxOpportunitiesPerAccount();

    @GetMapping("min-opportunities-per-account")
    Optional<Double>findMinOpportunitiesPerAccount();

}
