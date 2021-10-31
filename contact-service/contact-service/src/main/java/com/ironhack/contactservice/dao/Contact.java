package com.ironhack.contactservice.dao;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String name;

    protected String phoneNumber;

    protected String email;

    protected String companyName;

    private Long salesId;

    private Long accountId;

    @ElementCollection
    private List<Long> opportunityId = new ArrayList<>();

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
