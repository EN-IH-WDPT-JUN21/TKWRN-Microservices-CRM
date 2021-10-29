package com.ironhack.leadservice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LeadDTO {

    @NotNull
    protected Long id;

    protected String name;

    protected String phoneNumber;

    protected String email;

    protected String companyName;

    private Long salesId;

    public LeadDTO(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }
}
