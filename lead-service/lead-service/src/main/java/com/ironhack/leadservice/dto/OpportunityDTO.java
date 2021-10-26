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

    private Long id;

    private Status status = Status.OPEN;

    private Truck product;

    private Integer quantity;

    private Long decisionMaker;

//    private Long accountId;

    private Long salesId;

    public void setTruck(Truck product) {
        this.product = product;
    }
}
