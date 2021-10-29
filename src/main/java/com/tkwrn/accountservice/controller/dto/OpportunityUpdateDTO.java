package com.tkwrn.accountservice.controller.dto;

import com.tkwrn.accountservice.dao.Account;
import com.tkwrn.accountservice.enums.Industry;
import com.tkwrn.accountservice.enums.Status;
import com.tkwrn.accountservice.enums.Truck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityUpdateDTO {

    private Long accountId;


}

