package com.ironhack.contactservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ContactDTO {

    private Long id;

    protected String name;

    protected String phoneNumber;

    protected String email;

    protected String companyName;

    private Long salesId;

    private Long accountId;

    private List<Long> opportunityId = new ArrayList<>();

    public ContactDTO(Long id, String name, String phoneNumber, String email, String companyName, Long salesId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
    }

    public ContactDTO(Long id, String name, String phoneNumber, String email, String companyName, Long salesId, Long accountId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
        this.accountId = accountId;
    }

    public ContactDTO(Long accountId) {
        this.accountId = accountId;
    }
}



