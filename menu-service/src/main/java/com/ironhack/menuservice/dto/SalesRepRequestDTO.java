package com.ironhack.menuservice.dto;

import com.ironhack.menuservice.dto.OpportunityRequestDTO;
import com.ironhack.menuservice.exceptions.EmptyStringException;
import com.ironhack.menuservice.exceptions.ExceedsMaxLength;
import com.ironhack.menuservice.exceptions.IdContainsLettersException;
import com.ironhack.menuservice.exceptions.NameContainsNumbersException;
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
