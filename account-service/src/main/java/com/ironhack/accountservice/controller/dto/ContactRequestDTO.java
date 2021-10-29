package com.ironhack.accountservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDTO {

    private Long id;

    @Column(name="contact_name")
    protected String name;


    protected String phoneNumber;

    protected String email;


    protected String companyName;


    private Long salesRepId;


//    private Long accountId;

//    private List<Long> opportunityListId;


    public ContactRequestDTO(Long id, String name, String phoneNumber, String email, String companyName) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }
}
