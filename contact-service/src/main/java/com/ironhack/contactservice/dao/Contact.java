package com.ironhack.contactservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private Long salesId;
    private Long accountId;
    @ElementCollection
    private List<Long> opportunityId = new ArrayList();

    public Contact(String name, String phoneNumber, String email, String companyName, Long salesId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
        this.accountId = null;
    }

    public Contact(Long id, String name, String phoneNumber, String email, String companyName, Long salesId, Long accountId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
        this.accountId = accountId;
    }
}
