package com.ironhack.accountservice.controller.interfaces;

import com.ironhack.accountservice.controller.dto.AccountReceiptDTO;
import com.ironhack.accountservice.dao.Account;

import java.util.List;

public interface IAccountController {
//    List<Account> getAccounts();
    List<AccountReceiptDTO> getAccounts();
}
