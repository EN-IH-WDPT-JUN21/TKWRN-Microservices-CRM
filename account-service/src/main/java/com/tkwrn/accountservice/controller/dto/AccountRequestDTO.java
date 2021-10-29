package com.tkwrn.accountservice.controller.dto;

import com.tkwrn.accountservice.enums.Industry;
import com.tkwrn.accountservice.exceptions.EmptyStringException;
import com.tkwrn.accountservice.exceptions.ExceedsMaxLength;
import com.tkwrn.accountservice.exceptions.InvalidCountryException;
import com.tkwrn.accountservice.exceptions.NameContainsNumbersException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

    private Long opportunityId;

    private String industryName;

    public Industry getIndustry() {
        return Industry.valueOf(industryName);
    }

    public void setIndustry(Industry industry) {
        this.industryName = industry.toString();
    }

    @Column(name="employee_count")
    private Integer employeeCount;

    private String city;
    private String country;

//    private List<Long> contactListId;
//
//    private List<Long> opportunityListId;

    public AccountRequestDTO(String industryName, Integer employeeCount, String city, String country) {
        this.industryName = industryName;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }

    //    public AccountRequestDTO(String industryName, Integer employeeCount, String city, String country, List<Long> contactListId, List<Long> opportunityListId) {
//        this.industryName = industryName;
//        this.employeeCount = employeeCount;
//        this.city = city;
//        this.country = country;
//        this.contactListId = contactListId;
//        this.opportunityListId = opportunityListId;
//    }
}
