package com.example.banking.controller;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account with account number "+aNr+" not found.");
    }
    @GetMapping("/accounts/customer-accounts/{kNr}")
    public Object[] getCustomerAccountByKNr(
            @PathVariable Integer kNr
    ){
        String empty = "Either customer with customer number " + kNr+ " does not exist or the customer has no accounts.";
        List<AccountResponse> account = bankingService.findAccountByKNr(kNr);
        if (account.isEmpty())
            return new String[]{empty};
        else
            return bankingService.findAccountByKNr(kNr).toArray();

    }

    @GetMapping("/accounts/{aNr}/balance")
    public ResponseEntity<Double> getBalanceInEuro(
            @PathVariable Integer aNr
    ){Optional<AccountResponse> account = bankingService.findByANr(aNr);
        if (account.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(bankingService.getBalanceInEuro(aNr));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/accounts/{aNr}/deposit/{amount}")
    public ResponseEntity<String> depositAmount(
            @PathVariable Integer aNr,
            @PathVariable Double amount
    ){
        Optional<AccountResponse> account = bankingService.findByANr(aNr);
        if (account.isPresent()) {
            bankingService.depositAmount(aNr, amount);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deposit success.\n\nPrevious balance: "+Math.round((account.get().getBalanceInEuro()-amount)*100.0)/100.0+ "€\nAmount: "+amount+"€ \nCurrent balance: "+Math.round(account.get().getBalanceInEuro()*100.0)/100.0 +"€");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deposit amount failed. Account with account number "+aNr+ " does not exist.");

    }

    @PutMapping("/accounts/{aNr}/withdraw/{amount}")
    public ResponseEntity<String> withdrawAmount(
            @PathVariable Integer aNr,
            @PathVariable Double amount
    ){
        Optional<AccountResponse> account = bankingService.findByANr(aNr);
        if (account.isPresent()){
            if (account.get().getBalanceInEuro()-amount >= 0){

            bankingService.withdrawAmount(aNr, amount);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Withdraw success.\n\nPrevious balance: "+Math.round((account.get().getBalanceInEuro()+amount)*100.0)/100.0+ "€\nAmount: "+amount+"€ \nCurrent balance: "+Math.round(account.get().getBalanceInEuro()*100.0)/100.0 +"€");
        }
            else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Could not withdraw.\n\nRequested amount: "+amount +"€\nCurrent balance: "+Math.round(account.get().getBalanceInEuro()*100.0)/100.0+"€ \n\nAmount is bigger than current balance.");
            }
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Withdraw amount failed. Account with account number "+aNr+ " does not exist.");
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
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account with account number "+aNr+" deleted.");

    }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Account with account number "+aNr+" does not exist.");

    }

}
