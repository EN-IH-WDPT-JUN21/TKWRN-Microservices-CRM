package com.ironhack.accountservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SalesRepDTO {

    private Long id;

    @Column(name="sales_rep_name")
    private String repName;
}
