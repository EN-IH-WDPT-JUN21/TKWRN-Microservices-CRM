package com.ironhack.salesrepservice.dao;

import com.ironhack.salesrepservice.dto.LeadDTO;
import com.ironhack.salesrepservice.dto.OpportunityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales_rep")
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="sales_rep_name")
    private String repName;

    /*@ElementCollection
    private List<String> leadList;

    @ElementCollection
    private List<String> opportunityList;*/

}
