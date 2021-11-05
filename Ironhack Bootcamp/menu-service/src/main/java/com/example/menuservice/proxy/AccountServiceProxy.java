package com.example.menuservice.proxy;



import com.example.menuservice.dto.AccountReceiptDTO;
import com.example.menuservice.dto.AccountRequestDTO;
import com.example.menuservice.dto.AccountUpdateDTO;
import com.example.menuservice.exceptions.EmptyStringException;
import com.example.menuservice.exceptions.ExceedsMaxLength;
import com.example.menuservice.exceptions.InvalidCountryException;
import com.example.menuservice.exceptions.NameContainsNumbersException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("account-service")
@RequestMapping("/api/v1/accounts")
public interface AccountServiceProxy {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountReceiptDTO> getAccounts();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountReceiptDTO findAccountById (@PathVariable(name="id") Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountReceiptDTO create(@RequestBody @Valid AccountRequestDTO accountRequestDTO);

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountReceiptDTO createAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO);

    @PutMapping("/change/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountReceiptDTO updateAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid AccountUpdateDTO accountUpdateDTO);

    @GetMapping("/populate")
    public void populate() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength;
}
