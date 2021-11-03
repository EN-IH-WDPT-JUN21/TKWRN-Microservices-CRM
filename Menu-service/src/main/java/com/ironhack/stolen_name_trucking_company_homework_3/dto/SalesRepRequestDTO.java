package com.ironhack.stolen_name_trucking_company_homework_3.dto;

import com.ironhack.stolen_name_trucking_company_homework_3.dto.OpportunityRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.EmptyStringException;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.ExceedsMaxLength;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.IdContainsLettersException;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NameContainsNumbersException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
