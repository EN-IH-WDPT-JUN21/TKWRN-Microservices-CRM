package com.ironhack.leadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ContactDTO {

    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    private String companyName;

    private Long salesId;

    private Long accountId;

    @ElementCollection
    private List<Long> opportunityId = new ArrayList<>();

    public ContactDTO(String name, String phoneNumber, String email, String companyName, Long salesId, Long accountId, List<Long> opportunityId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
        this.accountId = accountId;
        this.opportunityId = opportunityId;
    }

    public ContactDTO(String name, String phoneNumber, String email, String companyName,
                      Long salesId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
    }
}

