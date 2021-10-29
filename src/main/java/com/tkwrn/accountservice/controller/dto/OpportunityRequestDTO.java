package com.tkwrn.accountservice.controller.dto;

import com.tkwrn.accountservice.dao.Account;
import com.tkwrn.accountservice.enums.Industry;
import com.tkwrn.accountservice.enums.Status;
import com.tkwrn.accountservice.enums.Truck;
import com.tkwrn.accountservice.exceptions.ExceedsMaxLength;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

//    private Long accountId;


    private Long salesRepId;


    public OpportunityRequestDTO(Long id, Status status, Truck product, Integer quantity, Long decisionMakerId) {
        this.id = id;
        this.status = status;
        this.product = product;
        this.quantity = quantity;
        this.decisionMakerId = decisionMakerId;
    }
}
