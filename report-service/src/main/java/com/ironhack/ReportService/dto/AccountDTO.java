package com.ironhack.ReportService.dto;

import com.ironhack.ReportService.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;

    private Industry industry;

    private Integer employeeCount;

    private String city;

    private String country;

    private List<Long> contactIdList = new ArrayList<>();

    private List<Long> opportunityIdList = new ArrayList<>();
}
