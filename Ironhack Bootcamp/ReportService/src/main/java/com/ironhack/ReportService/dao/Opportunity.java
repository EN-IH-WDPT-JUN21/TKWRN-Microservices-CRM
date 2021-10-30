package com.ironhack.ReportService.dao;//Extends com.ironhack.stolen_name_trucking_company_homework_3.ClientInformation class to retain Unique ID incrementing.

import com.ironhack.ReportService.enums.Status;
import com.ironhack.ReportService.enums.Truck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opportunity")
public class Opportunity {

    @Id
    private Long id;

    // This sets the status to Enum Open whenever an opportunity object is created
    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    @Enumerated(EnumType.STRING)
    private Truck product;
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name = "decision_maker", referencedColumnName = "id")
    private Contact decisionMaker;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    @ManyToOne
    @JoinColumn(name = "sales_rep_id", referencedColumnName = "id")
    private SalesRep salesRep;


}
