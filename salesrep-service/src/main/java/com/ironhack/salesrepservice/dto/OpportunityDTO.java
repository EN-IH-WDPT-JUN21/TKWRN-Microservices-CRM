package com.ironhack.salesrepservice.dto;

import com.ironhack.salesrepservice.dao.SalesRep;
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
    private String status;
    private String product;
    private int quantity;
    private String decisionMaker;
    private String account;
    private SalesRep salesRep;
}
