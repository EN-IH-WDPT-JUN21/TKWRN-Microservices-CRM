package com.ironhack.ReportService.dao;//For creating basic Leads. Extends Client information in order to retain a unique ID counter.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "leads")
public class Lead {

    @Id
    protected Long id;

    @Column(name="contact_name")
    protected String name;

    @Column(name= "phone_number")
    protected String phoneNumber;

    protected String email;

    @Column(name="company_name")
    protected String companyName;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id", referencedColumnName = "id")
    private SalesRep salesRep;


}


