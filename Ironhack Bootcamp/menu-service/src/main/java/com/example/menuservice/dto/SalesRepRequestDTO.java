package com.example.menuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesRepRequestDTO {

    private Long id;
    private String repName;

    @ElementCollection
    private List<Long> leadList;

    @ElementCollection
    private List<Long> opportunityList;

    public SalesRepRequestDTO(Long id, String repName) {
        this.id = id;
        this.repName = repName;
    }
}
