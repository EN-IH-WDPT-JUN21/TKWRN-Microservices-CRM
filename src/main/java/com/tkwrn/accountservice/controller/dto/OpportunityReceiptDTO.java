package com.tkwrn.accountservice.controller.dto;

import com.tkwrn.accountservice.dao.Account;
import com.tkwrn.accountservice.enums.Status;
import com.tkwrn.accountservice.enums.Truck;
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
public class OpportunityReceiptDTO {
    private Long id;


    @Enumerated(EnumType.STRING)
    private Status status;


    private Truck product;

    private Integer quantity;

    private Long decisionMakerId;


//    private ContactReceiptDTO decisionMaker;

    private Long accountId;

//    private Account account;

    private Long salesRepId;


//    private SalesRepDTO salesRepDTO;


}
