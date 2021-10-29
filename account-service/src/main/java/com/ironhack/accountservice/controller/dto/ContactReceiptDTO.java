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

    @Column(name="contact_name")
    protected String name;


    protected String phoneNumber;

    protected String email;


    protected String companyName;


    private Long salesRepId;
//    private SalesRepDTO salesRepDTO;


    private Long accountId;
//    private Account account;

    private List<Long> opportunityListId;
//    private List<OpportunityReceiptDTO> opportunity;



}
