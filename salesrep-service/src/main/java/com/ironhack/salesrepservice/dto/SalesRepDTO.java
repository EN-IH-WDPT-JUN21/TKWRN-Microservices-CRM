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
    private List<Long> leadIdList;

    @ElementCollection
    private List<Long> opportunityIdList;

    public SalesRepDTO(Long id, String repName) {
        this.id = id;
        this.repName = repName;
    }
}
