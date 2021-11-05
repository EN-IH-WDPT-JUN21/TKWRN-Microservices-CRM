package com.ironhack.contactservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    public ContactDTO(Long id, String name, String phoneNumber, String email, String companyName, Long salesId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
    }
    
    public ContactDTO(Long accountId) {
        this.accountId = accountId;
    }
}
