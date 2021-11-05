package com.ironhack.leadservice.dao;

import com.ironhack.leadservice.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "contact_name")
    protected String name;

    @Column(name = "phone_number")
    protected String phoneNumber;

    protected String email;

    @Column(name = "company_name")
    protected String companyName;

    protected Long salesId;

    public Lead(String name, String phoneNumber, String email, String companyName, Long salesId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesId = salesId;
    }

    public Lead(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
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

//    public String toString() {
//        System.out.printf(String.format("%-1s %-17s %-1s %-50s %-1s %-27s %-1s %-47s %-1s %-50s %-1s \n",
//                colorMain + "║",
//                colorHeadlineBold + "ID",
//                colorMain + "║",
//                colorHeadlineBold + "Name",
//                colorMain + "║",
//                colorHeadlineBold + "Phone Number",
//                colorMain + "║",
//                colorHeadlineBold + "Email Address",
//                colorMain + "║",
//                colorHeadlineBold + "Company Name",
//                colorMain + "║\n" +
//                        colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════╬══════════════════════════════════════════╬═════════════════════════════════════════════╣"
//                        + reset));
//        return  String.format("%-1s %-15s %-1s %-48s %-1s %-25s %-1s %-45s %-1s %-48s %-1s\n",
//                colorMain + "║",
//                colorTable + getId(),
//                colorMain + "║",
//                colorTable + getName().toUpperCase(),
//                colorMain + "║",
//                colorTable + getPhoneNumber(),
//                colorMain + "║",
//                colorTable + getEmail().toUpperCase(),
//                colorMain + "║",
//                colorTable + getCompanyName().toUpperCase(),
//                colorMain + "║",
//                colorTable + getSalesRep().getId().toString(),
//                colorMain + "║"+ reset);
//    }
}
