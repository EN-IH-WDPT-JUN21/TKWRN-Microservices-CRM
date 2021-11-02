package com.ironhack.accountservice.controller.dto;

import com.ironhack.accountservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

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

    public AccountRequestDTO(String industryName, Integer employeeCount, String city, String country) {
        this.industryName = industryName;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }

}
