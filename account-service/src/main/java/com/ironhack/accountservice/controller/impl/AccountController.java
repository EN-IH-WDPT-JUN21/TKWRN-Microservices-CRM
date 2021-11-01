package com.ironhack.accountservice.controller.impl;

import com.ironhack.accountservice.controller.dto.AccountReceiptDTO;
import com.ironhack.accountservice.controller.dto.AccountRequestDTO;
import com.ironhack.accountservice.controller.dto.AccountUpdateDTO;
import com.ironhack.accountservice.controller.interfaces.IAccountController;
import com.ironhack.accountservice.dao.Account;
import com.ironhack.accountservice.exceptions.ExceedsMaxLength;
import com.ironhack.accountservice.repository.AccountRepository;
import com.ironhack.accountservice.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController implements IAccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IAccountService accountService;

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Account> getAccounts(){
//
//        return accountService.getAccounts();
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountReceiptDTO> getAccounts(){

        return accountService.getAccountsWithLists();
    }


//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Account findAccountById (@PathVariable(name="id") Long id){
//        return accountService.findAccountById(id);
//    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountReceiptDTO findAccountById (@PathVariable(name="id") Long id){
        return accountService.findAccountByIdWithLists(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountReceiptDTO create(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        return accountService.store(accountRequestDTO);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountReceiptDTO createAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) throws ExceedsMaxLength {
        return accountService.createAccount(accountRequestDTO);
    }

    @PutMapping("/change/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountReceiptDTO updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid AccountUpdateDTO accountUpdateDTO) throws ExceedsMaxLength {
        return accountService.updateAccount(id, accountUpdateDTO.getOpportunityId());
    }


    @GetMapping("reports/MEAN-EMPLOYEE-COUNT")
    @ResponseStatus(HttpStatus.OK)
    public Double findMeanEmployeeCount(){
        return accountService.findMeanEmployeeCount();
    }

    @GetMapping("reports/MEDIAN-EMPLOYEE-COUNT")
    @ResponseStatus(HttpStatus.OK)
    public int findMedianEmployeeCount(){
        return accountService.findMedianEmployeeCount();
    }

    @GetMapping("reports/MAX-EMPLOYEE-COUNT")
    @ResponseStatus(HttpStatus.OK)
    public int findMaxEmployeeCount(){
        return accountService.findMaxEmployeeCount();
    }

    @GetMapping("reports/MIN-EMPLOYEE-COUNT")
    @ResponseStatus(HttpStatus.OK)
    public int findMinEmployeeCount(){
        return accountService.findMinEmployeeCount();
    }
}
