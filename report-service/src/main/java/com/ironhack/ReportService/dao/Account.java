package com.ironhack.ReportService.dao;

import com.ironhack.ReportService.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Table(name = "account_report")
public class Account {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Industry industry;
    @Column(name="employee_count")
    private Integer employeeCount;

    private String city;
    private String country;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "account")
    private List<Contact> contactList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "account")
    private List<Opportunity> opportunityList = new ArrayList<>();


}

