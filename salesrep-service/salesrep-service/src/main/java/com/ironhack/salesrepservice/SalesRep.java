package com.ironhack.salesrepservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "sales_rep_name")
    private String repName;

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany(mappedBy = "salesRep")
//    private List<Lead> leadList;
//
//    @OneToMany(mappedBy = "salesRep")
//    private List<Opportunity> opportunityList;


    public SalesRep(String repName) {
        this.repName = repName;
    }
}