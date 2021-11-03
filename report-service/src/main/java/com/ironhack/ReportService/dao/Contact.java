package com.ironhack.ReportService.dao;

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
@Entity
@Table(name = "contact_report")
public class Contact{

    //public static Map<String, Contact> theContacts = new HashMap<>();
    @Id
    private Long id;

    @Column(name="contact_name")
    protected String name;

    @Column(name= "phone_number")
    protected String phoneNumber;

    protected String email;

    @Column(name="company_name")
    protected String companyName;

    private Long salesRepId;

    private Long accountId;

    @ElementCollection
    private List<Long> opportunityIdList = new ArrayList<>();



}
