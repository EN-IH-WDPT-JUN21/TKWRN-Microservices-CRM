package com.ironhack.ReportService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactTO {

    private Long id;

    protected String name;

    protected String phoneNumber;

    protected String email;

    protected String companyName;
}
