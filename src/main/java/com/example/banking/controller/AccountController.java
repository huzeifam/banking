package com.example.banking.controller;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import com.example.banking.service.AccountService;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AccountController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;

    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAllAccounts() {

        return accountService.findAllAccounts();
    }

    @GetMapping("/accounts/{aNr}")
    public ResponseEntity<Object> getAccountByANr(
            @PathVariable Integer aNr
    ) {
        Optional<AccountResponse> account = accountService.findByANr(aNr);
        if (account.isPresent())
            return ResponseEntity.ok(account.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account with account number " + aNr + " not found.");
    }

    @GetMapping("/accounts/customer-accounts/{kNr}")
    public Object[] getCustomerAccountByKNr(
            @PathVariable Integer kNr
    ) {
        String empty = "Either customer with customer number " + kNr + " does not exist or the customer has no accounts.";
        List<AccountResponse> account = accountService.findAccountByKNr(kNr);
        if (account.isEmpty())
            return new String[]{empty};
        else
            return accountService.findAccountByKNr(kNr).toArray();

    }

    @GetMapping("/accounts/{aNr}/balance")
    public ResponseEntity<Double> getBalanceInEuro(
            @PathVariable Integer aNr
    ) {
        Optional<AccountResponse> account = accountService.findByANr(aNr);
        if (account.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(accountService.getBalanceInEuro(aNr));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @PutMapping("/accounts/{aNr}/deposit/{amount}")
    public ResponseEntity<String> depositAmount(
            @PathVariable Integer aNr,
            @PathVariable Double amount
    ) {
        Optional<AccountResponse> account = accountService.findByANr(aNr);
        if (account.isPresent()) {
            accountService.depositAmount(aNr, amount);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deposit success.\n\n" +
                    "Previous balance: " + Math.round(account.get().getBalanceInEuro() * 100.0) / 100.0 + "€\n" +
                    "Amount: " + amount + "€ \n" +
                    "Current balance: " + Math.round((account.get().getBalanceInEuro()+amount) * 100.0) / 100.0 + "€");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deposit amount failed. Account with account number " + aNr + " does not exist.");

    }

    @PutMapping("/accounts/{aNr}/withdraw/{amount}")
    public ResponseEntity<String> withdrawAmount(
            @PathVariable Integer aNr,
            @PathVariable Double amount
    ) {
        Optional<AccountResponse> account = accountService.findByANr(aNr);
        if (account.isPresent()) {
            if (account.get().getBalanceInEuro() - amount >= 0) {

                accountService.withdrawAmount(aNr, amount);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Withdraw success.\n\n" +
                        "Previous balance: " + Math.round((account.get().getBalanceInEuro()) * 100.0) / 100.0 + "€\n" +
                        "Amount: " + amount + "€ \n" +
                        "Current balance: " + Math.round((account.get().getBalanceInEuro()-amount) * 100.0) / 100.0 + "€");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Could not withdraw.\n\n" +
                        "Requested amount: " + amount + "€\n" +
                        "Current balance: " + Math.round(account.get().getBalanceInEuro() * 100.0) / 100.0 + "€ \n\n" +
                        "Amount is bigger than current balance.");
            }
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Withdraw amount failed. Account with account number " + aNr + " does not exist.");
    }

    @PutMapping("/accounts/{aNr}/transfer/{newANr}/{amount}")
    public ResponseEntity<String> transferAmount(
            @PathVariable Integer aNr,
            @PathVariable Integer newANr,
            @PathVariable Double amount
    ) {
        Optional<AccountResponse> account1 = accountService.findByANr(aNr);
        Optional<AccountResponse> account2 = accountService.findByANr(newANr);

        if (account1.isPresent()) {
            if (account2.isPresent()) {
                if (account1.get().getaNr() != account2.get().getaNr()) {
                    if (account1.get().getBalanceInEuro() - amount >= 0) {
                        accountService.transferAmount(aNr, newANr, amount);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Transfer success. Transferred amount: " + amount + "€\n\n" +
                                "Transferring account with account number \"" + aNr + "\":\n" +
                                "Previous balance: " + Math.round(account1.get().getBalanceInEuro() * 100.0) / 100.0 + "€\n" +
                                "Current balance: " + Math.round((account1.get().getBalanceInEuro()-amount) * 100.0) / 100.0 + "€\n\n" +
                                "Receiving account with account number \"" + newANr + "\":\n" +
                                "Previous balance: " + Math.round(account2.get().getBalanceInEuro()  * 100.0) / 100.0 + "€\n" +
                                "Current balance: " + Math.round((account2.get().getBalanceInEuro()+ amount) * 100.0) / 100.0 + "€");
                    } else
                        return ResponseEntity.status(HttpStatus.CONFLICT).body("Transfer failed.\n" +
                                "Requested amount for transfer: " + amount + "€\n\n" +
                                "Current balance of transferring account with account number \"" + aNr + "\": " + Math.round(account1.get().getBalanceInEuro() * 100.0) / 100.0 + "€\n" +
                                "Current balance of receiving account with account number \"" + newANr + "\": " + Math.round(account2.get().getBalanceInEuro() * 100.0) / 100.0 + "€\n\n" +
                                "Amount is bigger than current balance of transferring account.");
                } else
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Transferring account and receiving account are the same!");

            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transfer failed. The receiving account with account number " + newANr + " does not exist.");
        } else if (account2.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transfer failed. The transferring account with account number " + aNr + " does not exist.");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transfer failed. Accounts with account numbers \"" + aNr + "\" and \"" + newANr + "\" don't exist.");


    }

    @PostMapping("/accounts")
    public AccountResponse createAccount(
            @RequestBody AccountCreateRequest aRequest
    ) {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.DE)
                .buildRandom();


        AccountResponse acct = new AccountResponse(
                aRequest.getkNr(),
                UUID.randomUUID().hashCode() & Integer.MAX_VALUE,
                (iban.getCountryCode()+ iban.getCheckDigit()+iban.getBban()).replaceAll("(\\w\\w\\w\\w)(\\w\\w\\w\\w)(\\w\\w\\w\\w)(\\w\\w\\w\\w)(\\w\\w\\w\\w)(\\w\\w)","$1 $2 $3 $4 $5 $6"),
                0.0,
                LocalDateTime.now()
        );
        return accountService.createAccount(acct);

    }

    @DeleteMapping("/accounts/{aNr}")
    public ResponseEntity deleteAccount(
            @PathVariable Integer aNr

    ) {
        Optional<AccountResponse> account = accountService.findByANr(aNr);

        if (account.isPresent()) {
            accountService.deleteByaNr(aNr);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account with account number " + aNr + " deleted.");

        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Account with account number " + aNr + " does not exist.");

    }

}
