package com.ironhack.leadservice.dao;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "contact_name")
    protected String name;

    @Column(name = "phone_number")
    protected String phoneNumber;

    protected String email;

    @Column(name = "company_name")
    protected String companyName;

    protected Long salesId;

    public Lead(String name, String phoneNumber, String email, String companyName, Long salesId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
    }

    public Lead(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }
}
