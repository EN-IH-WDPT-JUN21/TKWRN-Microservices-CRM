package com.ironhack.menuservice.dto;

import com.ironhack.menuservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountReceiptDTO {

    private Long id;

    private Industry industry;

    @Column(name="employee_count")
    private Integer employeeCount;

    private String city;
    private String country;

    private List<ContactReceiptDTO> contactList;

    private List<OpportunityReceiptDTO> opportunityList;

    public AccountReceiptDTO(Long id, Industry industry, Integer employeeCount, String city, String country) {
        this.id=id;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }
}
