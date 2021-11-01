package com.ironhack.accountservice.service.interfaces;

import com.ironhack.accountservice.controller.dto.AccountReceiptDTO;
import com.ironhack.accountservice.controller.dto.AccountRequestDTO;
import com.ironhack.accountservice.dao.Account;
import com.ironhack.accountservice.exceptions.EmptyStringException;
import com.ironhack.accountservice.exceptions.ExceedsMaxLength;
import com.ironhack.accountservice.exceptions.InvalidCountryException;
import com.ironhack.accountservice.exceptions.NameContainsNumbersException;

import java.util.List;

public interface IAccountService {
    List<Account> getAccounts();
    Account findAccountById (Long id);
    AccountReceiptDTO store(AccountRequestDTO accountRequestDTO);
    AccountReceiptDTO createAccount(AccountRequestDTO accountRequestDTO) throws ExceedsMaxLength;
    AccountReceiptDTO updateAccount(Long id, Long opportunityId) throws ExceedsMaxLength;
    Double findMeanEmployeeCount();
    int findMedianEmployeeCount();
    int findMaxEmployeeCount();
    int findMinEmployeeCount();
}
