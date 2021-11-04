package com.ironhack.leadservice.proxies;

import com.ironhack.leadservice.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("report-service")
public interface ReportServiceProxy {

    @PostMapping("/api/v1/report-db/post/leads")
    String createLeadDatabase(@RequestBody List<LeadDTO> leadDTOList);
}
