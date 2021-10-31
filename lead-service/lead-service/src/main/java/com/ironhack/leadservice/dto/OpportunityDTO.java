package com.ironhack.leadservice.dto;

import com.ironhack.leadservice.enums.Status;
import com.ironhack.leadservice.enums.Truck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityDTO {

    private Status status = Status.OPEN;

    private Truck product;

    private Integer quantity;

    private Long decisionMaker;

    private Long salesId;

    private Long accountId;

    public OpportunityDTO(Status status, Truck product, Integer quantity, Long decisionMaker, Long salesId) {
        this.status = status;
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.salesId = salesId;
    }
}

