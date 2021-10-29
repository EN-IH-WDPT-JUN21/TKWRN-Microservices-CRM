package com.tkwrn.accountservice.service.interfaces;

import com.tkwrn.accountservice.controller.dto.AccountReceiptDTO;
import com.tkwrn.accountservice.controller.dto.AccountRequestDTO;
import com.tkwrn.accountservice.controller.dto.AccountUpdateDTO;
import com.tkwrn.accountservice.dao.Account;
import com.tkwrn.accountservice.exceptions.EmptyStringException;
import com.tkwrn.accountservice.exceptions.ExceedsMaxLength;
import com.tkwrn.accountservice.exceptions.InvalidCountryException;
import com.tkwrn.accountservice.exceptions.NameContainsNumbersException;

import java.util.List;

public interface IAccountService {
    List<Account> getAccounts();
    Account findAccountById (Long id);
    AccountReceiptDTO store(AccountRequestDTO accountRequestDTO);
    AccountReceiptDTO createAccount(AccountRequestDTO accountRequestDTO) throws ExceedsMaxLength;
    AccountReceiptDTO updateAccount(Long id, Long opportunityId) throws ExceedsMaxLength;
    void populate() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength;
    Double findMeanEmployeeCount();
    int findMedianEmployeeCount();
    int findMaxEmployeeCount();
    int findMinEmployeeCount();
}
