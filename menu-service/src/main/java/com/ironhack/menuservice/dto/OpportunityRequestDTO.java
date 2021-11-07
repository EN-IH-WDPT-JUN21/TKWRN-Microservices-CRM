package com.ironhack.menuservice.dto;//Extends com.ironhack.menuservice.ClientInformation class to retain Unique ID incrementing.

import com.ironhack.menuservice.dto.AccountRequestDTO;
import com.ironhack.menuservice.dto.ContactRequestDTO;
import com.ironhack.menuservice.enums.Status;
import com.ironhack.menuservice.enums.Truck;
import com.ironhack.menuservice.exceptions.ExceedsMaxLength;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityRequestDTO {

    private Long id;

    private Truck product;

    private Integer quantity;

    private Long decisionMakerId;

    private Long accountId;

    private Long salesRepId;

    public OpportunityRequestDTO(OpportunityRequestDTO opp) {
        this.id = opp.getId();
        this.product = opp.getProduct();
        this.quantity = opp.getQuantity();
        this.decisionMakerId = opp.getDecisionMakerId();
        this.accountId = opp.getAccountId();
        this.salesRepId = opp.getSalesRepId();
    }

}
