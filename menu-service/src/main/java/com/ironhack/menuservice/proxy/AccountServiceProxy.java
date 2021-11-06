package com.ironhack.menuservice.proxy;

import com.ironhack.menuservice.dto.AccountReceiptDTO;
import com.ironhack.menuservice.dto.AccountRequestDTO;
import com.ironhack.menuservice.dto.AccountUpdateDTO;
import com.ironhack.menuservice.exceptions.EmptyStringException;
import com.ironhack.menuservice.exceptions.ExceedsMaxLength;
import com.ironhack.menuservice.exceptions.InvalidCountryException;
import com.ironhack.menuservice.exceptions.NameContainsNumbersException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("account-service")
public interface AccountServiceProxy {

    @GetMapping("/api/v1/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountReceiptDTO> getAccounts();

    @GetMapping("/api/v1/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountReceiptDTO findAccountById (@PathVariable(name="id") Long id);

    @PostMapping("/api/v1/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountReceiptDTO create(@RequestBody @Valid AccountRequestDTO accountRequestDTO);

    @PostMapping("/api/v1/accounts/new")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountReceiptDTO createAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO);

    @PutMapping("/api/v1/accounts/change/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountReceiptDTO updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid AccountUpdateDTO accountUpdateDTO);

    @GetMapping("/api/v1/accounts/populate")
    public void populate() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength;
}
