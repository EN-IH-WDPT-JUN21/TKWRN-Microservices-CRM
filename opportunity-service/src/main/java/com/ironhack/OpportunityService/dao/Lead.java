package com.ironhack.OpportunityService.dao;//For creating basic Leads. Extends Client information in order to retain a unique ID counter.

import com.ironhack.OpportunityService.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
public class Lead {

    protected String name;

    protected String phoneNumber;

    protected String email;

    protected String companyName;


    private static final String colorMain = "\u001B[33m";
    private static final String colorMainBold = "\033[1;37m";
    private static final String colorTable = "\u001B[32m";
    private static final String colorHeadlineBold = "\033[1;34m";
    private static final String reset = "\u001B[0m";


    public Lead(String name, String phoneNumber, String email, String companyName) throws NameContainsNumbersException, EmptyStringException, PhoneNumberContainsLettersException, EmailNotValidException, ExceedsMaxLength {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }


    public void setName(String name) throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
        if (name.isEmpty()) {
            throw new EmptyStringException("No name input. Please try again.");
        }
        else if(!name.matches("[a-zA-Z\\u00C0-\\u00FF\\s]+")){
            throw new NameContainsNumbersException( "Name can not contain numbers. Please try again.");
        } else if(name.length()>43){
            throw new ExceedsMaxLength("Exceeds maximum value of 43 characters. Please try again.");
        }

        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) throws EmptyStringException, PhoneNumberContainsLettersException, ExceedsMaxLength {


        if (phoneNumber.isEmpty()) {
            throw new EmptyStringException("No Phone Number input. Please try again.");
        }
        else if(!phoneNumber.matches("[0-9]+")) {
            throw new PhoneNumberContainsLettersException("The phone number must only contain numbers. Please try again.");
        } else if(phoneNumber.length()>20) {
            throw new ExceedsMaxLength("Exceeds maximum value of 20 characters. Please try again.");
        }

        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailNotValidException, EmptyStringException, ExceedsMaxLength {
        if (email.isEmpty()) {
            throw new EmptyStringException("No email input. Please, try again.");
        }
        else if (!EmailValidator.getInstance().isValid(email)){
            throw new EmailNotValidException("The email should follow the format \"***@***.**\". Please, try again.");
        } else if (email.length()>40){
            throw new ExceedsMaxLength("Exceeds maximum value of 40 characters. Please, try again.");
        }

        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) throws EmptyStringException, ExceedsMaxLength {
        if (companyName.isEmpty()) {
            throw new EmptyStringException("No company name input. Please, try again.");
        } else if (companyName.length()>43){
            throw new ExceedsMaxLength("Exceeds maximum value of 43 characters. Please, try again.");
        }

        this.companyName = companyName;
    }

    public String toString() {
        System.out.printf(String.format("%-1s %-17s %-1s %-50s %-1s %-27s %-1s %-47s %-1s %-50s %-1s \n",
                                        colorMain + "║",
                                        colorHeadlineBold + "ID",
                                        colorMain + "║",
                                        colorHeadlineBold + "Name",
                                        colorMain + "║",
                                        colorHeadlineBold + "Phone Number",
                                        colorMain + "║",
                                        colorHeadlineBold + "Email Address",
                                        colorMain + "║",
                                        colorHeadlineBold + "Company Name",
                                        colorMain + "║\n" +
                                        colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════╬══════════════════════════════════════════╬═════════════════════════════════════════════╣"
                                        + reset));
        return  String.format("%-1s %-15s %-1s %-48s %-1s %-25s %-1s %-45s %-1s %-48s %-1s\n",
                              colorMain + "║",
                              colorTable + getName().toUpperCase(),
                              colorMain + "║",
                              colorTable + getPhoneNumber(),
                              colorMain + "║",
                              colorTable + getEmail().toUpperCase(),
                              colorMain + "║",
                              colorTable + getCompanyName().toUpperCase(),
                              colorMain + "║"+ reset);
    }
}


