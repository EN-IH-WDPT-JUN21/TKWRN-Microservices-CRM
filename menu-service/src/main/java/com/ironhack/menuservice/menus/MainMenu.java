package com.ironhack.menuservice.menus;

import com.ironhack.menuservice.dao.Login;
import com.ironhack.menuservice.dao.PopulateDatabase;
import com.ironhack.menuservice.dao.Variables;
import com.ironhack.menuservice.dto.*;
import com.ironhack.menuservice.enums.Industry;
import com.ironhack.menuservice.enums.Status;
import com.ironhack.menuservice.enums.Truck;
import com.ironhack.menuservice.exceptions.*;
import com.ironhack.menuservice.proxy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

@Component
@EnableFeignClients
public class MainMenu implements Variables {

    @Autowired
    ReportMainMenu reportMainMenu;

    @Autowired
    SalesRepReportMenu salesRepReportMenu;

    @Autowired
    private AccountServiceProxy accountServiceProxy;

    @Autowired
    private LeadServiceProxy leadServiceProxy;

    @Autowired
    private OpportunityServiceProxy opportunityServiceProxy;

    @Autowired
    private ReportDBServiceProxy reportDBServiceProxy;

    @Autowired
    private SalesRepServiceProxy salesRepServiceProxy;

    @Autowired
    private ContactServiceProxy contactServiceProxy;

    public MainMenu(AccountServiceProxy accountServiceProxy, LeadServiceProxy leadServiceProxy, OpportunityServiceProxy opportunityServiceProxy, ReportDBServiceProxy reportDBServiceProxy, SalesRepServiceProxy salesRepServiceProxy, ContactServiceProxy contactServiceProxy) {
        this.accountServiceProxy = accountServiceProxy;
        this.leadServiceProxy = leadServiceProxy;
        this.opportunityServiceProxy = opportunityServiceProxy;
        this.reportDBServiceProxy = reportDBServiceProxy;
        this.salesRepServiceProxy = salesRepServiceProxy;
        this.contactServiceProxy = contactServiceProxy;
    }

    private static boolean wasRunFocus = false;
    private static boolean wasRunPopulate = false;
    private static boolean valid;


    public void OS() throws RuntimeException, AWTException, NoSuchValueException {

        System.out.println("\n" + colorHeadline + colorLogo
                + "                                                                                                \n" +
                "                                         *#### #####        ###################*   *####*         \n" +
                "                         #################### #####        ######################  #####          \n" +
                "                    ,######              ### #####        #####            ###### #####           \n" +
                "                  ####                  ### #####        #####    ############## #####            \n" +
                "                ####                   ### #####        #####      ###########  #####             \n" +
                "              ########################### #####        #####            ###### #####              \n" +
                "             ####################.###### ############ ###################### ############         \n" +
                "             ################ ####### # ############ #####################  ############          \n" + reset +
                colorHeadline + colorMain + "╔═══════════════════════════════════════════════════════════════════════════════════════════════════╗\n"
                + "║                                " + colorTable + "WELCOME TO LBL CRM SYSTEM" + colorMain + "                                          ║\n"
                + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + String.format("%-1s %-104s %-1s", "║", colorTable + "WHAT WOULD YOU LIKE TO DO " + Login.getUsername().toUpperCase() + "?", colorMain + /*insertLine(68) +*/ "║\n")
                + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + "║ 1.  To create new Lead " + colorHeadline + "- type: 'new lead'" + colorMain + "                                                         ║\n"
                + "║ 2.  To create new Sales Representative " + colorHeadline + "- type: 'new salesrep'" + colorMain + "                                     ║\n"
                + "║ 3.  To check Leads list " + colorHeadline + "- type: 'show leads'" + colorMain + "                                                      ║\n"
                + "║ 4.  To check individual Lead's details " + colorHeadline + "- type: 'lookup lead ' + Lead Id" + colorMain + "                           ║\n"
                + "║ 5.  To convert Lead into Opportunity " + colorHeadline + "- type: - 'convert ' + Lead Id" + colorMain + "                               ║\n"
                + "║ 6.  To check Opportunity list " + colorHeadline + "- type: 'show opportunities'" + colorMain + "                                        ║\n"
                + "║ 7.  To check individual Opportunity's details " + colorHeadline + "- type: 'lookup opportunity ' + Opportunity Id" + colorMain + "      ║\n"
                + "║ 8.  To change Opportunity status to WON" + colorHeadline + "- type: 'close-won' + Opportunity Id" + colorMain + "                       ║\n"
                + "║ 9.  To change Opportunity status to LOST" + colorHeadline + "- type: 'close-lost' + Opportunity Id" + colorMain + "                     ║\n"
                + "║ 10. To check Contact list " + colorHeadline + "- type: 'show contacts'" + colorMain + "                                                 ║\n"
                + "║ 11. To check Account list " + colorHeadline + "- type: 'show accounts'" + colorMain + "                                                 ║\n"
                + "║ 12. To check Sales Representatives list " + colorHeadline + "- type: 'show salesreps'" + colorMain + "                                  ║\n"
                + "║ 13. To check available Reports " + colorHeadline + "- type: 'view reports'" + colorMain + "                                             ║\n"
                + "║ 14. To populate Database " + colorHeadline + "- type: 'populate'" + colorMain + "                                                       ║\n"
                + "║ 15. To clear Database " + colorHeadline + "- type: 'clear'" + colorMain + "                                                             ║\n"
                + "║ 16. To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                        ║\n"
                + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        consoleFocusRunOnce();

        try {

            // Creates String array from scanner input
            String[] input = scanner.nextLine().trim().toLowerCase().split("\\s+");

            if (input[0].equals("quit")) {
                System.out.println(colorMainBold + "\nThank you for using our LBL CRM SYSTEM!" + reset);
                System.out.println(colorError + "Exiting the program" + reset);
                System.exit(0);
            } else if (input[0].equals("populate")) {
                System.out.println("populate");
                PopulateDatabase.populateDatabase();
            } else if (input.length < 2) {
                throw new IllegalArgumentException();
            } else if (input[0].equals("lookup") && input[1].equals("lead") && input.length > 2) {
//                if (!leadServiceProxy.existsById(Long.parseLong(input[2]))) {
//                    throw new NoSuchValueException("There is no Lead that matches that id.");
//                }
                lookUpLeadId(Long.parseLong(input[2]));
            } else if (input[0].equals("lookup") && input[1].equals("opportunity") && input.length > 2) {
//                if (!opportunityServiceProxy.existsById(Long.parseLong(input[2]))) {
//                    throw new NoSuchValueException("There is no Opportunity that matches that id.");
//                }
                lookUpOppId(input[2]);
            } else if (input[0].equals("convert")) { // throws null point exception if number not in array
//                if (!leadServiceProxy.existsById(Long.parseLong(input[1]))) {
//                    throw new NoSuchValueException("There is no Lead that matches that id.");
//                }
                createAccount(convertLead(input[1]));
            } else if (input[0].equals("close-lost")) {
//                if (!opportunityServiceProxy.existsById(Long.parseLong(input[1]))) {
//                    throw new NoSuchValueException("There is no Opportunity that matches that id.");
//                }
                closeLost(input[1]);
            } else if (input[0].equals("close-won")) {
//                if (!opportunityServiceProxy.existsById(Long.parseLong(input[1]))) {
//                    throw new NoSuchValueException("There is no Opportunity that matches that id.");
//                }
                closeWon(input[1]);
            } else {

                switch (input[0] + input[1]) {
                    case "new" + "lead" -> {
                        if (salesRepServiceProxy.getAllSalesReps().size() == 0){
                            System.out.println(colorError + "\nPlease create a Sales Representative before proceeding\n" + reset);
                            throw new NoSuchValueException("There are currently no Sales Representatives in the database");
                        }
                        newLead();
                    }
                    case "new" + "salesrep" -> newSalesRep();
                    case "show" + "leads" -> showLeads();
                    case "show" + "opportunities" -> showOpportunities();
                    case "show" + "contacts" -> showContacts();
                    case "show" + "accounts" -> showAccounts();
                    case "show" + "salesreps" -> showSalesReps();
                    case "view" + "reports" -> reportMainMenu.reportMainMenu();
                    case "main" + "menu" -> OS();
                    default -> throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(colorError + "\nInvalid input" + reset);
        } catch (NameContainsNumbersException | EmptyStringException | InvalidCountryException | EmailNotValidException | ExceedsMaxLength | PhoneNumberContainsLettersException | NoSuchValueException e) {
            System.out.println(colorError + e.getMessage() + reset);
        }

        System.out.println(colorInput + "\nPress Enter to continue..." + reset);
        scanner.nextLine();
        OS();

    }


    // Method to create a new lead
    public LeadRequestDTO newLead() {

        valid = false;

        System.out.println(colorInput + "\nWould you like to create a new lead?" + colorTable + "   y / n " + reset);
        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    LeadRequestDTO newLeadRequestDTO = new LeadRequestDTO();
                    leadServiceProxy.createLead(newLeadRequestDTO);

                    //checks if restrictions for Customer name are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's name: " + reset);
                        try {
                            newLeadRequestDTO.setName(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        } catch (Exception e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    //checks if restrictions for Phone number are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's phone number without spaces: " + reset);
                        try {
                            newLeadRequestDTO.setPhoneNumber(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        } catch (Exception e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;


                    //checks if restrictions for E-mail address are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's email address: " + reset);
                        try {
                            newLeadRequestDTO.setEmail(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        } catch (Exception e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    //checks if restrictions for Company name are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's company name: " + reset);
                        try {
                            newLeadRequestDTO.setCompanyName(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        } catch (Exception e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    //
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input Sales Representative id: " + reset);
                        try {
                            Optional<SalesRepRequestDTO> salesRep = Optional.ofNullable(salesRepServiceProxy.findById(Long.parseLong(scanner.nextLine().trim())));
                            if(salesRep.isEmpty()){
                                System.out.println(colorError + "\nInvalid Sales Representative id - please start again\n" + reset);
                            } else {
                                newLeadRequestDTO.setSalesId(salesRep.get().getId());
                                valid = true;
                            }
                        }catch(Exception e){
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    //theLeads.put(newLead.getId(), newLead);
                    System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "New Lead created" + colorMain + " ══════════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗" + reset);
                    System.out.println(newLeadRequestDTO);
                    leadServiceProxy.createLead(newLeadRequestDTO);
                    return newLeadRequestDTO;
                }
                case "n" -> OS();

                default -> throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException | AWTException | NoSuchValueException e) {

            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            newLead();
        }
        return null;
    }

    // Method to convert Lead to Opportunity
    public OpportunityRequestDTO convertLead(String id) throws NullPointerException {

        LeadRequestDTO leadRequestDTO = leadServiceProxy.getLeadById(Long.parseLong(id));
        System.out.println(colorInput + "\nWould you like to convert " +
                colorTable + leadRequestDTO.getName().toUpperCase() +
                colorInput + " from " +
                colorTable + leadRequestDTO.getCompanyName().toUpperCase() +
                colorInput + " into an opportunity?" +
                colorTable + "    y / n " + reset);
        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    OpportunityRequestDTO newOpp = new OpportunityRequestDTO();
                    opportunityServiceProxy.createOpportunity(newOpp);

                    valid = false;

                    // checks if restrictions for Product are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the product that " + colorTable + leadRequestDTO.getCompanyName().toUpperCase() + colorInput + " is interested in: \n " +
                                colorTable + "HYBRID, FLATBED OR BOX" + reset);
                        try {
                            newOpp.setProduct(Truck.getTruck(scanner.nextLine().trim().toUpperCase(Locale.ROOT)));
                            valid = true;
                        } catch (EmptyStringException | InvalidEnumException e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    // checks if restrictions for Quantity are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the quantity that " + colorTable + leadRequestDTO.getCompanyName().toUpperCase() + colorInput + " is interested in: " + reset);

                        try {
                            newOpp.setQuantity(Integer.parseInt(scanner.nextLine().trim()));
                            valid = true;
                        } catch (NumberFormatException e) {
                            System.out.println(colorError + "You need to input a reasonable number. Please, try again.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    LeadRequestDTO newContactRequestDTO = new LeadRequestDTO(leadRequestDTO.getName().toUpperCase(), leadRequestDTO.getPhoneNumber().toUpperCase(), leadRequestDTO.getEmail().toUpperCase(), leadRequestDTO.getCompanyName().toUpperCase(), leadRequestDTO.getSalesId()); // Converts lead into contact
                    contactServiceProxy.createContact(newContactRequestDTO);
                    newOpp.setDecisionMakerId(newContactRequestDTO.getId()); // Assigns contact as the decisionMaker
                    newOpp.setSalesRepId(leadRequestDTO.getSalesId());
                    opportunityServiceProxy.createOpportunity(newOpp);
//                    leadServiceProxy.delete(leadRequestDTO);
                    System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "New Opportunity created" + colorMain + " ════════════╦═══════════════════╗" + reset);
                    System.out.printf("%-1s %-17s %-1s %-27s %-1s %-24s %-1s %-24s %-1s\n",
                            colorMain + "║",
                            colorHeadlineBold + "ID",
                            colorMain + "║",
                            colorHeadlineBold + "Status",
                            colorMain + "║",
                            colorHeadlineBold + "Product",
                            colorMain + "║",
                            colorHeadlineBold + "Quantity",
                            colorMain + "║\n" +
                                    colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════╣");
                    System.out.println(newOpp);
                    System.out.println(colorInput + "Press Enter to continue..." + reset);
                    scanner.nextLine();
                    System.out.println(colorMain + "╔════════════╦═════ " + colorMainBold + "New Contact created" + colorMain + " ═══════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗" + reset);
                    System.out.printf(String.format("%-1s %-17s %-1s %-50s %-1s %-27s %-1s %-47s %-1s %-50s %-1s\n",
                            colorMain + "║",
                            colorHeadlineBold + "ID",
                            colorMain + "║",
                            colorHeadlineBold + "Name",
                            colorMain + "║",
                            colorHeadlineBold + "Phone Number",
                            colorMain + "║",
                            colorHeadlineBold + "Email Address",
                            colorMain + "║",
                            colorHeadlineBold + "Company name",
                            colorMain + "║\n" +
                                    colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════╬══════════════════════════════════════════╬═════════════════════════════════════════════╣" + reset));
                    System.out.println(newContactRequestDTO);
                    System.out.println(colorInput + "Press Enter to continue..." + reset);
                    scanner.nextLine();
                    return newOpp;
                }
                case "n" -> OS();
                default -> throw new IllegalArgumentException(colorError + "Invalid input - please start again" + reset);
            }
        } catch (Exception e) {

            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            convertLead(id); // Catches errors and returns to start of method - Is there a simple alternative?
        }
        return null;
    }

    // Method called to create a new account
    public AccountReceiptDTO createAccount(OpportunityRequestDTO opportunityRequestDTO) {
        System.out.println(colorInput + "Would you like to create a new Account?" + colorTable + " y / n" + reset);
        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    //AccountRequestDTO newAccountRequestDTO = new AccountRequestDTO(opportunityRequestDTO.getDecisionMakerId(), opportunityRequestDTO);
                    AccountRequestDTO newAccountRequestDTO = new AccountRequestDTO(opportunityRequestDTO);
                    AccountReceiptDTO accountReceiptDTO = accountServiceProxy.createAccount(newAccountRequestDTO);
                    valid = false;

                        // checks if restrictions for Industry are met
                        while (!valid) {
                            System.out.println(colorInput + "\nPlease input the company industry: \n" +
                                    colorTable + "PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL OR OTHER" + reset);
                            try {
                                newAccountRequestDTO.setIndustry(Industry.getIndustry(scanner.nextLine().trim().toUpperCase(Locale.ROOT))); // ENUM Selection
                                valid = true;
                            } catch (EmptyStringException | InvalidEnumException e) {
                                System.out.println(colorError + e.getMessage());
                            }
                        }

                        valid = false;

                        // checks if restrictions for Employee count are met
                        while (!valid) {
                            System.out.println(colorInput + "\nPlease input the employee count for " + colorTable + accountReceiptDTO.getContactList().get(0).getCompanyName() + colorInput + ":  " + reset); //**Needs amending to display name in contact list
                            try {
                                newAccountRequestDTO.setEmployeeCount(Integer.parseInt(scanner.nextLine().trim()));
                                valid = true;
                            } catch (NumberFormatException e) {
                                System.out.println(colorError + "You need to input a reasonable number. Please, try again.");
                            } catch (IllegalArgumentException e) {
                                System.out.println(colorError + e.getMessage());
                            }
                        }

                        valid = false;

                        // checks if restrictions for City name are met
                        while (!valid) {
                            System.out.println(colorInput + "\nPlease input the city for " + colorTable + accountReceiptDTO.getCity() + colorInput + ":  " + reset);
                            try {
                                newAccountRequestDTO.setCity(scanner.nextLine().trim().toUpperCase(Locale.ROOT));
                                valid = true;
                            } catch (Exception e) {
                                System.out.println(colorError + e.getMessage());
                            }
                        }

                        valid = false;

                        // checks if Country is in country array
                        while (!valid) {
                            System.out.println(colorInput + "\nPlease input the Country for " + colorTable + accountReceiptDTO.getCountry() + ":  " + reset);
                            try {
                                newAccountRequestDTO.setCountry(scanner.nextLine().trim().toUpperCase());
                                valid = true;
                            } catch (Exception e) {
                                System.out.println(colorError + e.getMessage());
                            }
                        }

                        valid = false;

                        // Assigns the Account to the contact(decision maker) of the opportunity
                        opportunityRequestDTO.setAccountId(accountReceiptDTO.getId());
                        //contactServiceProxy.updateContact();
                        //accountServiceProxy.save(newAccountRequestDTO);
                        System.out.println(accountReceiptDTO);

                        return accountReceiptDTO;
                }
                case "n" -> {
                    valid = false;
                    if(accountServiceProxy.getAccounts().isEmpty()){
                        System.out.println(colorError + "There are no Accounts. Please create a new Account");
                        createAccount(opportunityRequestDTO);
                    }
                    while (!valid) {
                        System.out.println(colorInput + "Please, input the account number you wish to link the " + colorTable + "Opportunity " + opportunityRequestDTO.getId() + colorInput + " to: " + reset);
                        try {
                            opportunityRequestDTO.setAccountId(Long.parseLong(scanner.nextLine().trim()));
                            OpportunityReceiptDTO oppReceipt = opportunityServiceProxy.createOpportunity(opportunityRequestDTO);
                            //contactServiceProxy.save(opportunityRequestDTO.getDecisionMaker());
                            valid = true;
                            System.out.println(colorInput + "The Opportunity has been linked to " + colorTable + contactServiceProxy.getContactById(oppReceipt.getDecisionMakerId()).getCompanyName() + reset);
                            return accountServiceProxy.findAccountById(oppReceipt.getAccountId());
                        } catch (Exception e) {
                            System.out.println(colorError + "There is no account with this number. Please, try again" + reset);
                        }
                    }
                }
                default -> throw new IllegalArgumentException(colorError + "Invalid input - please start again" + reset);
            }
        } catch (Exception e) {
            System.out.println(colorError + "\nInvalid input - please start again\n");
            createAccount(opportunityRequestDTO);
        }
        return null;
    }

    // showing all leads
    public void showLeads() {
        var allLeads = leadServiceProxy.getLeads();
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Leads: " + allLeads.size() + colorMain + " ════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Name",
                colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-45s%-1s\n",
                colorMain + "╠",
                "════════════",
                "╬",
                "═════════════════════════════════════════════",
                "╣" + reset);
        for (int i = 0; i < allLeads.size(); i++) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                    colorMain + "║",
                    colorTable + allLeads.get(i).getId(),
                    colorMain + "║",
                    colorTable + allLeads.get(i).getName(),
                    colorMain + "║" + reset);
        }
    }

    // showing all contacts
    public void showContacts() {
        var allContacts = contactServiceProxy.getContacts();
        System.out.println(colorMain + "\n╔════════════╦════════ " + colorMainBold + "Total Number Of Contacts: " + allContacts.size() + colorMain + " ════════╦══════════════════════════════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-50s %-1s %-47s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Name",
                colorMain + "║",
                colorHeadlineBold + "Company name",
                colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-45s%-1s%-32s%-1s\n",
                colorMain + "╠",
                "════════════",
                "╬",
                "═════════════════════════════════════════════",
                "╬",
                "══════════════════════════════════════════",
                "╣" + reset);
        for (int i = 0; i < allContacts.size(); i++) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s %-47s %-1s\n",
                    colorMain + "║",
                    colorTable + allContacts.get(i).getId(),
                    colorMain + "║",
                    colorTable + allContacts.get(i).getName(),
                    colorMain + "║",
                    colorTable + allContacts.get(i).getCompanyName(),
                    colorMain + "║" + reset);
        }
    }

    public void showOpportunities() {
        var allOpps = opportunityServiceProxy.getAll();
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Total Number Of Opportunities: " + allOpps.size() + colorMain + " ══════╦══════════════════════════════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-24s %-1s %-17s %-1s %-17s %-1s %-47s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Contract status",
                colorMain + "║",
                colorHeadlineBold + "Product",
                colorMain + "║",
                colorHeadlineBold + "Quantity",
                colorMain + "║",
                colorHeadlineBold + "Decision maker",
                colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-19s%-1s%-12s%-1s%-12s%-1s%-42s%-1s\n",
                colorMain + "╠",
                "════════════",
                "╬",
                "═══════════════════",
                "╬",
                "════════════",
                "╬",
                "════════════",
                "╬",
                "══════════════════════════════════════════",
                "╣" + reset);
        for (int i = 0; i < allOpps.size(); i++) {
            System.out.printf("%-1s %-17s %-1s %-24s %-1s %-17s %-1s %-17s %-1s %-47s %-1s\n",
                    colorMain + "║",
                    colorTable + allOpps.get(i).getId(),
                    colorMain + "║",
                    colorTable + allOpps.get(i).getStatus(),
                    colorMain + "║",
                    colorTable + allOpps.get(i).getProduct(),
                    colorMain + "║",
                    colorTable + allOpps.get(i).getQuantity(),
                    colorMain + "║",
                    colorTable + allOpps.get(i).getDecisionMakerId(),
                    colorMain + "║" + reset);
        }
    }

    public void showAccounts() {
        var allAccs = accountServiceProxy.getAccounts();
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Accounts: " + allAccs.size() + colorMain + " ═════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-24s %-1s %-17s %-1s %-17s %-1s %-47s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Industry",
                colorMain + "║",
                colorHeadlineBold + "Employee Count",
                colorMain + "║",
                colorHeadlineBold + "City",
                colorMain + "║",
                colorHeadlineBold + "Country",
                colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-19s%-1s%-12s%-1s%-12s%-1s%-42s%-1s\n",
                colorMain + "╠",
                "════════════",
                "╬",
                "═══════════════════",
                "╬",
                "════════════",
                "╬",
                "════════════",
                "╬",
                "══════════════════════════════════════════",
                "╣" + reset);
        for (int i = 0; i < allAccs.size(); i++) {
            System.out.printf("%-1s%-12s%-1s%-19s%-1s%-12s%-1s%-12s%-1s%-42s%-1s\n",
                    colorMain + "║",
                    colorTable + allAccs.get(i).getId(),
                    colorMain + "║",
                    colorTable + allAccs.get(i).getIndustry(),
                    colorMain + "║",
                    colorTable + allAccs.get(i).getEmployeeCount(),
                    colorMain + "║",
                    colorTable + allAccs.get(i).getCity(),
                    colorMain + "║",
                    colorTable + allAccs.get(i).getCountry(),
                    colorMain + "║" + reset);
        }
    }


    public void lookUpLeadId(Long id) throws RuntimeException {
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Lead details" + colorMain + " ══════════════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗" + reset);
        System.out.println(leadServiceProxy.getLeadById(id));
    }

    // lookup opportunity by Id
    public void lookUpOppId(String id) throws RuntimeException {
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Contract details" + colorMain + " ═╦═══════════════════╦═══════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-27s %-1s %-24s %-1s %-24s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Contract status",
                colorMain + "║",
                colorHeadlineBold + "Product",
                colorMain + "║",
                colorHeadlineBold + "Quantity",
                colorMain + "║\n" +
                        colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════╣\n" + reset +
                        opportunityServiceProxy.getById(Long.parseLong(id)) +
                        colorMain + "\n╔════════════╦═══ " + colorMainBold + "Decision maker details" + colorMain + " ══════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗\n" + reset +
                        String.format("%-1s %-17s %-1s %-50s %-1s %-27s %-1s %-47s %-1s %-50s %-1s\n",
                                colorMain + "║",
                                colorHeadlineBold + "ID",
                                colorMain + "║",
                                colorHeadlineBold + "Name",
                                colorMain + "║",
                                colorHeadlineBold + "Phone Number",
                                colorMain + "║",
                                colorHeadlineBold + "Email Address",
                                colorMain + "║",
                                colorHeadlineBold + "Company name",
                                colorMain + "║\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════╬══════════════════════════════════════════╬═════════════════════════════════════════════╣\n" + reset +
                                        opportunityServiceProxy.getById(Long.parseLong(id)).getDecisionMakerId().toString()));
    }


    //Change opportunity status to LOST
    public void closeLost(String id) {
        OpportunityReceiptDTO opp = opportunityServiceProxy.getById(Long.parseLong(id));
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Opportunity details" + colorMain + " ════════════════╦═══════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-27s %-1s %-24s %-1s %-24s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Status",
                colorMain + "║",
                colorHeadlineBold + "Product",
                colorMain + "║",
                colorHeadlineBold + "Quantity",
                colorMain + "║\n" +
                        colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════╣");
        System.out.println(opp);
        System.out.println(colorInput + "Would you like to change the status of this opportunity to " + colorTable + "LOST?   y / n" + reset);
        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    opp.setStatus(Status.CLOSED_LOST);
                    opportunityServiceProxy.createOpportunity(new OpportunityRequestDTO(opp)); //does it override or creates a new instance?
                    System.out.println(colorMain + "\n═════════════ " + colorMainBold + "Status Changed!" + colorMain + " ═════════════" + reset);
                }
                case "n" -> OS();

                default -> throw new IllegalArgumentException(colorError + "Invalid input - please try again" + reset);
            }

        } catch (Exception e) {
            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            closeLost(id);
        }
    }

    //Change opportunity status to Won
    public void closeWon(String id) {
        OpportunityReceiptDTO opp = opportunityServiceProxy.getById(Long.parseLong(id));
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Opportunity details" + colorMain + " ════════════════╦═══════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-27s %-1s %-24s %-1s %-24s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Status",
                colorMain + "║",
                colorHeadlineBold + "Product",
                colorMain + "║",
                colorHeadlineBold + "Quantity",
                colorMain + "║\n" +
                        colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════╣");
        System.out.println(opp);
        System.out.println(colorInput + "Would you like to change the status of this opportunity to " + colorTable + "WON?   y / n" + reset);
        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    opp.setStatus(Status.CLOSED_WON);
                    opportunityServiceProxy.createOpportunity(new OpportunityRequestDTO(opp));
                    System.out.println(colorMain + "\n═════════════ " + colorMainBold + "Status Changed!" + colorMain + " ═════════════" + reset);
                }
                case "n" -> OS();

                default -> throw new IllegalArgumentException(colorError + "Invalid input - please try again" + reset);
            }

        } catch (Exception e) {
            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            closeWon(id);
        }
    }

    // Focuses cursor inside console for IntelliJ users
    public void consoleFocus() throws AWTException {
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_4);
        robot.keyRelease(KeyEvent.VK_4);
        robot.keyRelease(KeyEvent.VK_ALT);
    }

    // Makes sure that method consoleFocus is run only once during the program runtime
    public void consoleFocusRunOnce() throws AWTException {
        if (!wasRunFocus) {
            wasRunFocus = true;
            consoleFocus();
        }
    }

    public void populateDatabaseRunOnce() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        if (!wasRunPopulate) {
            wasRunPopulate = true;
            PopulateDatabase.populateDatabase();
        }
    }

    public SalesRepRequestDTO newSalesRep() {

        valid = false;

        System.out.println(colorInput + "\nWould you like to create a new sales representative?" + colorTable + "   y / n " + reset);
        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    SalesRepRequestDTO newSalesRepRequestDTO = new SalesRepRequestDTO();

                    //asks and validates customer's name
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the sales representative's name: " + reset);
                        try {
                            newSalesRepRequestDTO.setRepName(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        } catch (Exception e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;
                    salesRepServiceProxy.addSalesRep(newSalesRepRequestDTO);
                    System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "New Sales Representative created" + colorMain + " ════════╗" + reset);
                    System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                                      colorMain + "║",
                                      colorHeadlineBold + "ID",
                                      colorMain + "║",
                                      colorHeadlineBold + "Name",
                                      colorMain + "║");
                    System.out.printf("%-1s%-12s%-1s%-45s%-1s\n",
                                      colorMain + "╠",
                                      "════════════",
                                      "╬",
                                      "═════════════════════════════════════════════",
                                      "╣" + reset);
                    System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                            colorMain + "║",
                            colorTable + newSalesRepRequestDTO.getId(),
                            colorMain + "║",
                            colorTable + newSalesRepRequestDTO.getRepName().toUpperCase(),
                            colorMain + "║" + reset);
                    return newSalesRepRequestDTO;
                }
                case "n" -> // Would normally go back in the menu at this point
                        OS();

                default -> throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException | AWTException | NoSuchValueException e) {

            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            newSalesRep();
        }
        return null;
    }

    public void showSalesReps() {
        var allReps = salesRepServiceProxy.getAllSalesReps();
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Sales Representatives: " + allReps.size() + colorMain + " ╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Name",
                colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-45s%-1s\n",
                colorMain + "╠",
                "════════════",
                "╬",
                "═════════════════════════════════════════════",
                "╣" + reset);
        for (int i = 0; i < allReps.size(); i++) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                    colorMain + "║",
                    colorTable + allReps.get(i).getId(),
                    colorMain + "║",
                    colorTable + allReps.get(i).getRepName(),
                    colorMain + "║" + reset);
        }
    }

    public void OSGuest() throws RuntimeException, AWTException, NameContainsNumbersException, EmptyStringException, InvalidCountryException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {

        System.out.println("\n" + colorHeadline + colorLogo
                + "                                                                                                \n" +
                "                                         *#### #####        ###################*   *####*         \n" +
                "                         #################### #####        ######################  #####          \n" +
                "                    ,######              ### #####        #####            ###### #####           \n" +
                "                  ####                  ### #####        #####    ############## #####            \n" +
                "                ####                   ### #####        #####      ###########  #####             \n" +
                "              ########################### #####        #####            ###### #####              \n" +
                "             ####################.###### ############ ###################### ############         \n" +
                "             ################ ####### # ############ #####################  ############          \n" + reset +
                colorHeadline + colorMain + "╔═══════════════════════════════════════════════════════════════════════════════════════════════════╗\n"
                + "║                                " + colorTable + "WELCOME TO LBL CRM SYSTEM" + colorMain + "                                          ║\n"
                + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + String.format("%-1s %-104s %-1s", "║", colorTable + "WHAT WOULD YOU LIKE TO DO " + Login.getUsername().toUpperCase() + "?", colorMain + /*insertLine(68) +*/ "║\n")
                + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + "║ 1.  To check Leads list " + colorHeadline + "- type: 'show leads'" + colorMain + "                                                      ║\n"
                + "║ 2.  To check individual Lead's details " + colorHeadline + "- type: 'lookup lead ' + Lead Id" + colorMain + "                           ║\n"
                + "║ 3.  To check Opportunity list " + colorHeadline + "- type: 'show opportunities'" + colorMain + "                                        ║\n"
                + "║ 4.  To check individual Opportunity's details " + colorHeadline + "- type: 'lookup opportunity ' + Opportunity Id" + colorMain + "      ║\n"
                + "║ 5.  To check Contact list " + colorHeadline + "- type: 'show contacts'" + colorMain + "                                                 ║\n"
                + "║ 6.  To check Account list " + colorHeadline + "- type: 'show accounts'" + colorMain + "                                                 ║\n"
                + "║ 7.  To check Sales Representatives list " + colorHeadline + "- type: 'show salesreps'" + colorMain + "                                  ║\n"
                + "║ 8.  To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                        ║\n"
                + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        consoleFocusRunOnce();
        populateDatabaseRunOnce();

        try {

            // Creates String array from scanner input
            String[] input = scanner.nextLine().trim().toLowerCase().split("\\s+");

            if (input[0].equals("quit")) {
                System.out.println(colorMainBold + "\nThank you for using our LBL CRM SYSTEM!" + reset);
                System.out.println(colorError + "Exiting the program" + reset);
                System.exit(0);
            } else if (input[0].equals("lookup") && input[1].equals("lead")) {
                lookUpLeadId(Long.parseLong(input[2]));
            } else if (input[0].equals("lookup") && input[1].equals("opportunity")) {
                lookUpOppId(input[2]);
            } else {

                switch (input[0] + input[1]) {
                    case "new" + "lead" -> newLead();
                    case "show" + "leads" -> showLeads();
                    case "show" + "opportunities" -> showOpportunities();
                    case "show" + "contacts" -> showContacts();
                    case "show" + "accounts" -> showAccounts();
                    case "show" + "salesreps" -> showSalesReps();
                    default -> throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(colorError + "\nInvalid input" + reset);

        }
        System.out.println(colorInput + "\nPress Enter to continue..." + reset);
        scanner.nextLine();
        OSGuest();
    }
}


