package com.ironhack.leadservice.dto;

import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.dto.OpportunityDTO;
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

    private List<Lead> leadList;

    @ElementCollection
    private List<Long> opportunityList;

    public SalesRepDTO(String repName, List<Lead> leadList, List<Long> opportunityList) {
        this.repName = repName;
        this.leadList = leadList;
        this.opportunityList = opportunityList;
    }

    public SalesRepDTO(Long id, String repName) {
        this.id = id;
        this.repName = repName;
    }
}
