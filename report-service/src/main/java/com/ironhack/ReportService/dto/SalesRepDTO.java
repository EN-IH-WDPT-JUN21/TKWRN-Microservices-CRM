package com.ironhack.ReportService.dto;

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
public class SalesRepDTO {

    private Long id;
    private String repName;
    private List<Long> leadIdList = new ArrayList<>();
    private List<Long> opportunityIdList = new ArrayList<>();
}
