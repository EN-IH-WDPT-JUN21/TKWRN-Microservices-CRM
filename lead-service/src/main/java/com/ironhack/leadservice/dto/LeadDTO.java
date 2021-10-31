package com.ironhack.leadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LeadDTO {

    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    private String companyName;

    private Long salesId;

    public LeadDTO(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }
}
