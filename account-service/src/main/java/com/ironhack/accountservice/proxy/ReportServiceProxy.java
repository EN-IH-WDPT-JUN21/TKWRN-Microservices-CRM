package com.ironhack.accountservice.proxy;

import com.ironhack.accountservice.dao.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("report-service")
public interface ReportServiceProxy {

    @PostMapping("/api/v1/report-db/post/accounts")
    String createAccountDatabase(@RequestBody List<Account> leadDTOList);

    @PostMapping("/api/v1/report-db/new/account")
    Account addOrUpdateAccount(@RequestBody Account leadDTO);
}
