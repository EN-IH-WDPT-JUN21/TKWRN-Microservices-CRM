package com.ironhack.accountservice.service.impl;

import com.ironhack.accountservice.controller.dto.*;
import com.ironhack.accountservice.exceptions.EmptyStringException;
import com.ironhack.accountservice.exceptions.ExceedsMaxLength;
import com.ironhack.accountservice.exceptions.InvalidCountryException;
import com.ironhack.accountservice.exceptions.NameContainsNumbersException;
import com.ironhack.accountservice.service.interfaces.IAccountService;
import com.ironhack.accountservice.dao.Account;
import com.ironhack.accountservice.enums.Industry;
import com.ironhack.accountservice.proxy.ContactProxy;
import com.ironhack.accountservice.proxy.OpportunityProxy;
import com.ironhack.accountservice.proxy.SalesRepProxy;
import com.ironhack.accountservice.repository.AccountRepository;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OpportunityProxy opportunityProxy;

    @Autowired
    ContactProxy contactProxy;

    @Autowired
    SalesRepProxy salesRepProxy;

    Logger logger = LoggerFactory.getLogger("AccountService.class");

    @Retry(name = "account-api", fallbackMethod = "fallbackAccountList")
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @Retry(name = "account-api", fallbackMethod = "fallbackAccountDTOList")
    public List<AccountReceiptDTO> getAccountsWithLists(){
        List<AccountReceiptDTO> accountReceiptDTOList = new ArrayList<>();
        List<Account> accounts = accountRepository.findAll();
        for(Account account: accounts){
            Long id = account.getId();
            var opps = opportunityProxy.getAllOppsByAccount(id);

            AccountReceiptDTO accountReceiptDTO= new AccountReceiptDTO(account.getId(), account.getIndustry(),account.getEmployeeCount(), account.getCity(),account.getCountry());
            if (!opps.isEmpty()){
                accountReceiptDTO.setOpportunityList(opps);
            }
            var contacts = contactProxy.getAllContactsByAccount(id);

            if (!contacts.isEmpty()){
                accountReceiptDTO.setContactList(contacts);
            }
            accountReceiptDTOList.add(accountReceiptDTO);
        }
        return accountReceiptDTOList;
    }

    @Retry(name = "account-api", fallbackMethod = "fallbackAccount")
    public Account findAccountById (Long id){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.isPresent()? optionalAccount.get() : null;
    }

    @Retry(name = "account-api", fallbackMethod = "fallbackAccountDTO")
    public AccountReceiptDTO findAccountByIdWithLists (Long id){
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if(optionalAccount.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no account with this number. Please, try again");

        }
        Account account = optionalAccount.get();

        var opps = opportunityProxy.getAllOppsByAccount(id);

        AccountReceiptDTO accountReceiptDTO= new AccountReceiptDTO(account.getId(), account.getIndustry(),account.getEmployeeCount(), account.getCity(),account.getCountry());
        if (!opps.isEmpty()){
            accountReceiptDTO.setOpportunityList(opps);
        }
            var contacts = contactProxy.getAllContactsByAccount(id);

            if (!contacts.isEmpty()){
                accountReceiptDTO.setContactList(contacts);
            }

        return accountReceiptDTO;
    }


    // to create simple Account w/o links
    @Retry(name = "account-api", fallbackMethod = "fallbackAccountDTO")
    public AccountReceiptDTO store(AccountRequestDTO accountRequestDTO) {
        try{
            Account account;
            account = new Account(accountRequestDTO.getIndustry(), accountRequestDTO.getEmployeeCount(), accountRequestDTO.getCity(), accountRequestDTO.getCountry());
            accountRepository.save(account);
            AccountReceiptDTO accountReceiptDTO = new AccountReceiptDTO(account.getId(), account.getIndustry(), account.getEmployeeCount(), account.getCity(), account.getCountry());
            return accountReceiptDTO;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account type value not valid.");
        }

    }

    // Method called to create a new account
    @Retry(name = "account-api", fallbackMethod = "fallbackAccountDTO")
    public AccountReceiptDTO createAccount(AccountRequestDTO accountRequestDTO) throws ExceedsMaxLength {
        List<OpportunityReceiptDTO> opportunityReceiptDTOS = new ArrayList<>();
        List<ContactReceiptDTO> contactReceiptDTOS= new ArrayList<>();
        OpportunityReceiptDTO opportunityDTO;
        ContactReceiptDTO contactReceiptDTO;
        try{
            opportunityDTO= opportunityProxy.getById(accountRequestDTO.getOpportunityId());
            opportunityReceiptDTOS.add(opportunityDTO);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunity with this id");
        }

        try{
            contactReceiptDTO= contactProxy.getContactById(opportunityDTO.getDecisionMakerId());
            contactReceiptDTOS.add(contactReceiptDTO);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no contact with this id");
        }


        Account newAccount = new Account();
        boolean valid = false;

        // checks if restrictions for Industry are met
        while (!valid) {

            try {
                newAccount.setIndustry(accountRequestDTO.getIndustry()); // ENUM Selection
                valid = true;
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please input the company industry: \n PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL OR OTHER");
            }
        }

        valid = false;

        // checks if restrictions for Employee count are met
        while (!valid) {

            try {
                newAccount.setEmployeeCount(accountRequestDTO.getEmployeeCount());
                valid = true;
            } catch (NumberFormatException | ExceedsMaxLength e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You need to input a reasonable number. Please, try again.");
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You need to input a number. Please, try again.");
            }
        }

        valid = false;

        // checks if restrictions for City name are met
        while (!valid) {
            try {
                newAccount.setCity(accountRequestDTO.getCity());
                valid = true;
            } catch (EmptyStringException | NameContainsNumbersException | ExceedsMaxLength e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please input the city for " + contactReceiptDTO.getCompanyName()+ ":  ");
            }
        }

        valid = false;

        // checks if Country is in country array
        while (!valid) {
            try {
                newAccount.setCountry(accountRequestDTO.getCountry());
                valid = true;
            } catch (EmptyStringException | ExceedsMaxLength | InvalidCountryException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please input the Country for " + contactReceiptDTO.getCompanyName()+ ":  ");
            }
        }

        valid = false;

        accountRepository.save(newAccount);
        System.out.println(newAccount);
        // Assigns the Account to the opportunity and contact(decision maker) of the opportunity
        opportunityDTO.setAccountId(newAccount.getId());
        Long accountId = newAccount.getId();
        OpportunityUpdateDTO opportunityUpdateDTO = new OpportunityUpdateDTO(accountId);
        opportunityProxy.updateAccount(opportunityDTO.getId(), opportunityUpdateDTO);
        contactReceiptDTO.setAccountId(newAccount.getId());
        ContactUpdateDTO contactUpdateDTO = new ContactUpdateDTO(accountId);
        contactProxy.updateContact(contactReceiptDTO.getId(), contactReceiptDTO);
//      contactRepository.save(opportunity.getDecisionMaker());
        AccountReceiptDTO accountReceiptDTO = new AccountReceiptDTO(newAccount.getId(), newAccount.getIndustry(), newAccount.getEmployeeCount(), newAccount.getCity(), newAccount.getCountry());
        accountReceiptDTO.setOpportunityList(opportunityReceiptDTOS);
        accountReceiptDTO.setContactList(contactReceiptDTOS);


        return accountReceiptDTO;
    }

    // Method called to assign existing account
    @Retry(name = "account-api", fallbackMethod = "fallbackAccountDTO")
    public AccountReceiptDTO updateAccount(Long id, Long opportunityId) throws ExceedsMaxLength {
        boolean valid = false;


        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no account with this number. Please, try again");

        }
        Account accountStored = optionalAccount.get();
        Long accountId = accountStored.getId();
        List<OpportunityReceiptDTO> opportunityReceiptDTOS = new ArrayList<>();
        List<ContactReceiptDTO> contactReceiptDTOS= new ArrayList<>();
        OpportunityReceiptDTO opportunityDTO;
        ContactReceiptDTO contactReceiptDTO;
        try{
            opportunityDTO= opportunityProxy.getById(opportunityId);
            opportunityReceiptDTOS.add(opportunityDTO);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no opportunity with this id");
        }

        try{
            contactReceiptDTO= contactProxy.getContactById(opportunityDTO.getDecisionMakerId());
            contactReceiptDTOS.add(contactReceiptDTO);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no contact with this id");
        }
        Long contactId = opportunityDTO.getDecisionMakerId();

        valid = false;

        System.out.println(accountStored);
        // Assigns the Account to the opportunity and contact(decision maker) of the opportunity
        opportunityDTO.setAccountId(accountStored.getId());

        OpportunityUpdateDTO opportunityUpdateDTO = new OpportunityUpdateDTO(accountId);
        opportunityProxy.updateAccount(opportunityId, opportunityUpdateDTO);
        contactReceiptDTO.setAccountId(accountStored.getId());
        ContactUpdateDTO contactUpdateDTO = new ContactUpdateDTO(accountId);
        contactProxy.updateContact(contactId, contactReceiptDTO);
        AccountReceiptDTO accountReceiptDTO = new AccountReceiptDTO(accountStored.getId(), accountStored.getIndustry(), accountStored.getEmployeeCount(), accountStored.getCity(), accountStored.getCountry());
        accountReceiptDTO.setOpportunityList(opportunityReceiptDTOS);
        accountReceiptDTO.setContactList(contactReceiptDTOS);

        return accountReceiptDTO;

    }



    //fallback

    public List<Account>  fallbackAccountList(Exception e) throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        logger.info("call account fallback method");
        List<Account> fallbackAccountList = new ArrayList<>();
        Account fallbackAccount = new Account(Industry.PRODUCE, 1, "", "This is a account fallback");
        fallbackAccountList.add(fallbackAccount);
        return fallbackAccountList;
    }

    public List<AccountReceiptDTO>  fallbackAccountDTOList(Exception e) throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        logger.info("call account fallback method");
        List<AccountReceiptDTO> fallbackAccountList = new ArrayList<>();
        AccountReceiptDTO fallbackAccount = new AccountReceiptDTO(1l,Industry.PRODUCE, 1, "", "This is a account fallback");
        fallbackAccountList.add(fallbackAccount);
        return fallbackAccountList;
    }

    public Account fallbackAccount(Exception e) throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        logger.info("call account fallback method");
        Account fallbackAccount = new Account(Industry.PRODUCE, 1, "", "This is a account fallback");
        return fallbackAccount;
    }

    public AccountReceiptDTO fallbackAccountDTO(Exception e) throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        logger.info("call account fallback method");
        AccountReceiptDTO fallbackAccount = new AccountReceiptDTO(1l, Industry.PRODUCE, 1, "", "This is a account fallback");
        return fallbackAccount;
    }

    public void fallbackAccountNull(Exception e){
        logger.info("call account fallback method");
    }

}
