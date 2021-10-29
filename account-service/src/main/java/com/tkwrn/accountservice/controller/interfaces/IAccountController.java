package com.tkwrn.accountservice.controller.interfaces;

import com.tkwrn.accountservice.dao.Account;

import java.util.List;

public interface IAccountController {
    List<Account> getAccounts();
}
