package com.ironhack.salesrepservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesRepDTO {

    private Long id;
    private String repName;

    @ElementCollection
    private List<Long> leadList;

    @ElementCollection
    private List<Long> opportunityList;
}
