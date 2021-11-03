package com.ironhack.stolen_name_trucking_company_homework_3.dto;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AccountRequestDTO {

    private Long opportunityId;

    private String industryName;

    public Industry getIndustry() {
        return Industry.valueOf(industryName);
    }

    public void setIndustry(Industry industry) {
        this.industryName = industry.toString();
    }

    private Integer employeeCount;

    private String city;
    private String country;

    public AccountRequestDTO(String industryName, Integer employeeCount, String city, String country) {
        this.industryName = industryName;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }

    public AccountRequestDTO(OpportunityRequestDTO opportunityRequestDTO) {
         this.opportunityId = opportunityRequestDTO.getId();
    }



}

