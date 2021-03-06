package com.ironhack.ReportService.dao;

import com.ironhack.ReportService.dto.SalesRepDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales_rep_report")
public class SalesRep {

    @Id
    private Long id;

    @Column(name="sales_rep_name")
    private String repName;

    @ElementCollection
    private List<Long> leadIdList;

    @ElementCollection
    private List<Long> opportunityIdList;


    public SalesRep(SalesRepDTO salesrepDTO) {
        this.id = salesrepDTO.getId();
        this.repName = salesrepDTO.getRepName();
        this.leadIdList = salesrepDTO.getLeadIdList();
        this.opportunityIdList = salesrepDTO.getOpportunityIdList();

    }
}
