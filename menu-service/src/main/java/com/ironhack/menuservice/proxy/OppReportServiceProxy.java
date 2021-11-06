package com.ironhack.menuservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@FeignClient("report-service")
public interface OppReportServiceProxy {

    @GetMapping("/api/v1/opportunity-report/count-by-rep")
    List<Object[]> findCountOpportunityByRepName();

    @GetMapping("/api/v1/opportunity-report/count-by-rep/{status}")
    List<Object[]> findCountOpportunityByRepNameForStatus(@PathVariable String status);

    @GetMapping("/api/v1/opportunity-report/count-by-product")
    List<Object[]> findCountOppForProduct();

    @GetMapping("/api/v1/opportunity-report/count-by-product/{status}")
    List<Object[]> findCountOpportunityByProductForStatus(@PathVariable Enum status);

    @GetMapping("/api/v1/opportunity-report/count-by-country")
    List<Object[]> findCountOppForCountry();

    @GetMapping("/api/v1/opportunity-report/count-by-country/{status}")
    List<Object[]> findCountOpportunityByCountryForStatus(@PathVariable String status);

    @GetMapping("/api/v1/opportunity-report/count-by-city")
    List<Object[]> findCountOppForCity();

    @GetMapping("/api/v1/opportunity-report/count-by-city/{status}")
    List<Object[]> findCountOpportunityByCityForStatus(@PathVariable  String status);

    @GetMapping("/api/v1/opportunity-report/count-by-industry")
    List<Object[]> findCountOppForIndustry();

    @GetMapping("/api/v1/opportunity-report/count-by-industry/{status}")
    List<Object[]> findCountOpportunityByIndustryForStatus(@PathVariable String status);

    @GetMapping("/api/v1/opportunity-report/mean-product-quantity")
    Optional<Double> findMeanProductQuantity();

    @GetMapping("/api/v1/opportunity-report/max-product-quantity")
    Optional<Integer> findMaxProductQuantity();

    @GetMapping("/api/v1/opportunity-report/min-product-quantity")
    Optional<Integer> findMinProductQuantity();

    @GetMapping("/api/v1/opportunity-report/mean-opportunities-per-account")
    Optional<Double>findMeanOpportunitiesPerAccount();

    @GetMapping("/api/v1/opportunity-report/median-opportunities-per-account")
    Double findMedianOppsPerAccountStep1();

    @GetMapping("/api/v1/opportunity-report/max-opportunities-per-account")
    Optional<Double>findMaxOpportunitiesPerAccount();

    @GetMapping("/api/v1/opportunity-report/min-opportunities-per-account")
    Optional<Double>findMinOpportunitiesPerAccount();

}
