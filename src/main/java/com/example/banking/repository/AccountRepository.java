package com.example.banking.repository;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import com.example.banking.model.CustomerCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                       roundAndFormat(arequest.getBalanceInEuro(), 2, Locale.GERMAN),
                       LocalDate.now())
                );
            return ResponseEntity.ok().build();
    }

    private String roundAndFormat(Double balanceInEuro, int i, Locale german) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance(german);
        nf.setMaximumFractionDigits(2);
        return nf.format(new BigDecimal(balanceInEuro));
    }

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
//    public Optional<AccountResponse> findAccountByKNr(Integer kNr) {
//        Optional<AccountResponse> account = accounts.stream()
//                .filter(a -> a.getkNr().equals(kNr)).findAny();
//        return account;
//    }


}
