package com.ironhack.stolen_name_trucking_company_homework_3.dto;//Extends com.ironhack.stolen_name_trucking_company_homework_3.ClientInformation class to retain Unique ID incrementing.

import com.ironhack.stolen_name_trucking_company_homework_3.dto.AccountRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.ContactRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.ExceedsMaxLength;
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

    public OpportunityRequestDTO(OpportunityReceiptDTO opp) {
        this.id = opp.getId();
        this.product = opp.getProduct();
        this.quantity = opp.getQuantity();
        this.decisionMakerId = opp.getDecisionMakerId();
        this.accountId = opp.getAccountId();
        this.salesRepId = opp.getSalesRepId();
    }

}
