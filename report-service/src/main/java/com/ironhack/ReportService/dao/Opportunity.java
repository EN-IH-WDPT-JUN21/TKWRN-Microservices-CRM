package com.ironhack.ReportService.dao;//Extends com.ironhack.menuservice.ClientInformation class to retain Unique ID incrementing.

import com.ironhack.ReportService.dto.OpportunityDTO;
import com.ironhack.ReportService.enums.Status;
import com.ironhack.ReportService.enums.Truck;
import com.ironhack.ReportService.repository.AccountRepository;
import com.ironhack.ReportService.repository.ContactRepository;
import com.ironhack.ReportService.repository.SalesRepRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opportunity_report")
public class Opportunity {

    @Id
    private Long id;

    // This sets the status to Enum Open whenever an opportunity object is created
    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    @Enumerated(EnumType.STRING)
    private Truck product;
    private Integer quantity;

    private Long decisionMakerId;

    private Long accountId;

    private Long salesRepId;

    public Opportunity(OpportunityDTO opportunityDTO) {
        this.id = opportunityDTO.getId();
        this.status = opportunityDTO.getStatus();
        this.product = opportunityDTO.getProduct();
        this.quantity = opportunityDTO.getQuantity();
        this.decisionMakerId = opportunityDTO.getDecisionMakerId();
        this.accountId = opportunityDTO.getAccountId();
        this.salesRepId = opportunityDTO.getSalesRepId();
    }


}
