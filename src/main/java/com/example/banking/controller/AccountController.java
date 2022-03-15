package com.example.banking.controller;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.service.BankingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    private final BankingService bankingService;



    public AccountController(BankingService bankingService) {
        this.bankingService = bankingService;

    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAllAccounts(){

        return bankingService.findAllAccounts();
    }

    @GetMapping("/accounts/{aNr}")
    public ResponseEntity<Object> getAccountByANr(
            @PathVariable Integer aNr
    ){
        Optional<AccountResponse> account = bankingService.findByANr(aNr);
        if (account.isPresent())
            return ResponseEntity.ok(account.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
    }

    @PostMapping("/accounts")
    public ResponseEntity<Object> createAccount(
            @RequestBody AccountCreateRequest arequest
    )  {
        return bankingService.createAccount(
                arequest
        );
    }
    @DeleteMapping("/accounts/{aNr}")
    public ResponseEntity deleteAccount(
            @PathVariable Integer aNr

    ){
        Optional<AccountResponse> account = bankingService.findByANr(aNr);

        if (account.isPresent()){
        bankingService.deleteByaNr(aNr);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account deleted");

    }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Account does not exist.");

    }

}
