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

    private Long id;

    private Industry industry;

    private Integer employeeCount;

    private String city;

    private String country;

}

