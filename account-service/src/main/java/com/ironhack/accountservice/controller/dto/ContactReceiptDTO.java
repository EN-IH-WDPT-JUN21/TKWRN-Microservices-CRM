package com.ironhack.accountservice.controller.dto;

//import com.tkwrn.accountservice.dao.Opportunity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactReceiptDTO {

    private Long id;

    protected String name;

    protected String phoneNumber;

    protected String email;


    protected String companyName;


    private Long salesRepId;


    private Long accountId;

    private List<Long> opportunityId;


    public ContactReceiptDTO(Long id, String name, String phoneNumber, String email, String companyName, Long salesRepId, Long accountId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRepId = salesRepId;
        this.accountId = accountId;
    }
}
