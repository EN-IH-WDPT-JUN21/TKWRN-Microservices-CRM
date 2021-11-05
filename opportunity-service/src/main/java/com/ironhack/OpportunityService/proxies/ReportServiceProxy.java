package com.ironhack.OpportunityService.proxies;

import com.ironhack.OpportunityService.dao.Opportunity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("report-service")
public interface ReportServiceProxy {

    @PostMapping("/api/v1/report-db/post/opportunities")
    String createOppDatabase(@RequestBody List<Opportunity> opportunityList);

    @PostMapping("/api/v1/report-db/new/opportunity")
    Opportunity addOrUpdateOpportunity(@RequestBody Opportunity opportunity);
}
