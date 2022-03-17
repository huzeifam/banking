package com.example.banking.repository;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountRepository {

    List<AccountResponse> accounts = new ArrayList<>();

    public List<AccountResponse> findAll() {

        return accounts;
    }

    public ResponseEntity<Object> save(AccountCreateRequest arequest) {

       accounts.add(
               new AccountResponse(
                       arequest.getkNr(),
                       UUID.randomUUID().hashCode() & Integer.MAX_VALUE,
                       arequest.getIban(),
                       arequest.getBalanceInEuro(),
                       LocalDate.now())
                );
            return ResponseEntity.ok().build();
    }

//    private double roundAndFormat(double balanceInEuro, int i, Locale german) {
//        java.text.NumberFormat nf = java.text.NumberFormat.getInstance(german);
//        nf.setMaximumFractionDigits(2);
//        return Math.round(balanceInEuro*100.0)/100.0;
//    }

    public void deleteByaNr(Integer aNr) {
        this.accounts = accounts.stream()
                .filter(p -> !p.getaNr().equals(aNr))
                .collect(Collectors.toList());
    }

    public void deleteAccountByKNr(Integer kNr){
        this.accounts =accounts.stream()
                .filter(a -> !a.getkNr().equals(kNr))
                .collect(Collectors.toList());
    }


    public Optional<AccountResponse> findByANr(Integer aNr) {
        Optional<AccountResponse> account = accounts.stream()
                .filter(c -> c.getaNr().equals(aNr))
                .findFirst();
        return account;
    }
    public List<AccountResponse> FindAccountByKNr(Integer kNr) {
        List<AccountResponse> account = accounts.stream()
                .filter(c -> c.getkNr().equals(kNr))
                .collect(Collectors.toList());
        return account;
    }

    public double findBalanceByANr(Integer aNr) {
        Optional<AccountResponse> account = accounts.stream()
                .filter(c -> c.getaNr().equals(aNr))
                .findFirst();
        double currentBalance = (account.get().getBalanceInEuro());
        return Math.round(currentBalance*100.0)/100.0;
    }


    public double saveBalanceByANr(Integer aNr, Double amount) {
        Optional<AccountResponse> account = accounts.stream()
                .filter(c -> c.getaNr().equals(aNr))
                .findFirst();
        double currentBalance = (account.get().getBalanceInEuro());
        double newBalance = currentBalance +Math.round(amount*100.0)/100.0;

        account.get().setBalanceInEuro(newBalance);
        return Math.round(newBalance*100.0)/100.0;
    }

    public double withdrawAmountByANr(Integer aNr, Double amount) {
        Optional<AccountResponse> account = accounts.stream()
                .filter(c -> c.getaNr().equals(aNr))
                .findFirst();
        double currentBalance = (account.get().getBalanceInEuro());
        double newBalance = currentBalance -Math.round(amount*100.0)/100.0;
        if (newBalance >= 0){
            account.get().setBalanceInEuro(newBalance);
            return Math.round(newBalance*100.0)/100.0;
        }
            return 0;
    }
}
