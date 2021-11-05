package com.ironhack.menuservice.dto;//For creating basic Leads. Extends Client information in order to retain a unique ID counter.

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LeadRequestDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private Long salesId;


    public LeadRequestDTO(String name, String phoneNumber, String email, String companyName, Long salesId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
    }
}


