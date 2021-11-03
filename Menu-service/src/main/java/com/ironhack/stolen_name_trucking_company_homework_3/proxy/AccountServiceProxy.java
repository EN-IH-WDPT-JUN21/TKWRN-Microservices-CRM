package com.ironhack.stolen_name_trucking_company_homework_3.proxy;

import com.ironhack.stolen_name_trucking_company_homework_3.dto.AccountReceiptDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.AccountRequestDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.dto.AccountUpdateDTO;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.EmptyStringException;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.ExceedsMaxLength;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.InvalidCountryException;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NameContainsNumbersException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("account-service")
@RequestMapping("/api/v1/opps")
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
