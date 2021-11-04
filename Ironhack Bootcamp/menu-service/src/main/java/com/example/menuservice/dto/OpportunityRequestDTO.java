package com.example.menuservice.dto;

import com.example.menuservice.enums.Truck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public OpportunityRequestDTO(OpportunityReceiptDTO opp) {
        this.id = opp.getId();
        this.product = opp.getProduct();
        this.quantity = opp.getQuantity();
        this.decisionMakerId = opp.getDecisionMakerId();
        this.accountId = opp.getAccountId();
        this.salesRepId = opp.getSalesRepId();
    }

}
