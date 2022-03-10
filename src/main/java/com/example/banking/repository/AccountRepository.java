package com.example.banking.repository;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import com.example.banking.model.CustomerResponse;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class AccountRepository {

    List<AccountResponse> accounts = new ArrayList<>();

    public List<AccountResponse> findAll() {
        return accounts;
    }

    public ResponseEntity<Object> save(AccountCreateRequest arequest) {

       accounts.add(
               new AccountResponse(
                       UUID.randomUUID().hashCode() & Integer.MAX_VALUE,
                       arequest.getIban(),
                       arequest.getBalanceInEuro(),
                       arequest.getStartDate())
                );
            return ResponseEntity.ok().build();
    }

    public void deleteByaNr(Integer aNr) {
        this.accounts = accounts.stream()
                .filter(p -> !p.getaNr().equals(aNr))
                .collect(Collectors.toList());
    }


    public Optional<AccountResponse> findByANr(Integer aNr) {
        Optional<AccountResponse> account = accounts.stream()
                .filter(c -> c.getaNr().equals(aNr))
                .findFirst();
        return account;
    }
}
