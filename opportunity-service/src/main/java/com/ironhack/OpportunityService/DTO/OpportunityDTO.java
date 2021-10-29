package com.ironhack.OpportunityService.DTO;

import com.ironhack.OpportunityService.enums.Status;
import com.ironhack.OpportunityService.enums.Truck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityDTO {

    private Status status = Status.OPEN;

    private Truck product;

    private Integer quantity;

    private Long decisionMakerId;

    private Long accountId;

    private Long salesRepId;

}
