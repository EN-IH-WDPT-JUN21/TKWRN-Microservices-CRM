package com.tkwrn.accountservice.controller.dto;

import com.tkwrn.accountservice.dao.Account;
import com.tkwrn.accountservice.exceptions.EmptyStringException;
import com.tkwrn.accountservice.exceptions.ExceedsMaxLength;
import com.tkwrn.accountservice.exceptions.NameContainsNumbersException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
