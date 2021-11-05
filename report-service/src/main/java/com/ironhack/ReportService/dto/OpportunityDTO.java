package com.ironhack.ReportService.dto;

import com.ironhack.ReportService.enums.Status;
import com.ironhack.ReportService.enums.Truck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityDTO {

    private Long id;

    private Status status = Status.OPEN;

    private Truck product;

    private Integer quantity;

    private Long decisionMakerId;

    private Long accountId;

    private Long salesRepId;

    public Long getDecisionMakerId() {
        return decisionMakerId;
    }
}
