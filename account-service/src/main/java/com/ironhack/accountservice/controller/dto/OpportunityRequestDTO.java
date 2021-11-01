package com.ironhack.accountservice.controller.dto;

import com.ironhack.accountservice.enums.Status;
import com.ironhack.accountservice.enums.Truck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityRequestDTO {
    private Long id;


    @Enumerated(EnumType.STRING)
    private Status status;


    private Truck product;


    private Integer quantity;


    private Long decisionMakerId;


    private Long salesRepId;


    public OpportunityRequestDTO(Long id, Status status, Truck product, Integer quantity, Long decisionMakerId) {
        this.id = id;
        this.status = status;
        this.product = product;
        this.quantity = quantity;
        this.decisionMakerId = decisionMakerId;
    }
}
