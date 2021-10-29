package com.tkwrn.accountservice.controller.dto;

import com.tkwrn.accountservice.dao.Account;
import com.tkwrn.accountservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactUpdateDTO {

    private Long accountId;


}
