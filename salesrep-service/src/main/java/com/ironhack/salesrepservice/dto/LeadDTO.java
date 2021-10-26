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
public class LeadDTO {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private SalesRep salesRep;
}
