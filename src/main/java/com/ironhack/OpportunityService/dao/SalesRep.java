package com.ironhack.OpportunityService.dao;

import com.ironhack.OpportunityService.exceptions.EmptyStringException;
import com.ironhack.OpportunityService.exceptions.ExceedsMaxLength;
import com.ironhack.OpportunityService.exceptions.IdContainsLettersException;
import com.ironhack.OpportunityService.exceptions.NameContainsNumbersException;
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
@NoArgsConstructor
public class SalesRep {

    private String repName;

    public SalesRep(String repName) {
        this.repName = repName;
    }


    public void setName(String repName) throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
        if (repName.isEmpty()) {
            throw new EmptyStringException("No name input. Please try again.");
        }
        else if(!repName.matches("[a-zA-Z\\u00C0-\\u00FF\\s]+")){
            throw new NameContainsNumbersException( "Name can not contain numbers. Please try again.");
        } else if(repName.length()>43){
            throw new ExceedsMaxLength("Exceeds maximum value of 43 characters. Please try again.");
        }

        this.repName = repName;
    }

}
