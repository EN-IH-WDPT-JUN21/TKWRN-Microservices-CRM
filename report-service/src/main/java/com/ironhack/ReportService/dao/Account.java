package com.ironhack.ReportService.dao;

import com.ironhack.ReportService.dto.AccountDTO;
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

    @ElementCollection
    private List<Long> contactIdList = new ArrayList<>();

    @ElementCollection
    private List<Long> opportunityIdList = new ArrayList<>();


    public Account(AccountDTO accountDTO) {
        this.id = accountDTO.getId();
        this.industry = accountDTO.getIndustry();
        this.employeeCount = accountDTO.getEmployeeCount();
        this.city = accountDTO.getCity();
        this.country = accountDTO.getCountry();
        this.contactIdList = accountDTO.getContactIdList();
        this.opportunityIdList = accountDTO.getOpportunityIdList();
    }

}

