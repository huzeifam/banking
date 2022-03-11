package com.example.banking.controller;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import com.example.banking.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    AccountService accountService = new AccountService();

    @GetMapping("/accounts")
    public List<AccountResponse> getAllAccounts(){
        return accountService.findAll();
    }

    @GetMapping("/accounts/{aNr}")
    public ResponseEntity<Object> getAccountByANr(
            @PathVariable Integer aNr
    ){
        Optional<AccountResponse> account = accountService.findByANr(aNr);
        if (account.isPresent())
            return ResponseEntity.ok(account.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/accounts")
    public ResponseEntity<Object> createAccount(
            @RequestBody AccountCreateRequest arequest
    )  {
        return accountService.createAccount(
                arequest
        );
    }
    @DeleteMapping("/accounts/{aNr}")
    public ResponseEntity deleteAccount(
            @PathVariable Integer aNr
    ){
        accountService.deleteByaNr(aNr);
        return ResponseEntity.noContent().build();
    }

}
