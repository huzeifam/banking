package com.example.banking.service;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    CustomerRepository customerRepository = new CustomerRepository();
    AccountRepository accountRepository = new AccountRepository();

    public List<AccountResponse> findAll() {
        return accountRepository.findAll();
    }

    public Optional<AccountResponse> findByANr(Integer aNr) {
        return accountRepository.findByANr(aNr);
    }

    public ResponseEntity<Object> createAccount(AccountCreateRequest arequest) {

        Optional<CustomerResponse> customer = customerRepository.findByKNr(arequest.getkNr());

        if (customer.isPresent()) {
            return ResponseEntity.notFound().build();

        }

        return accountRepository.save(arequest);
    }




    public void deleteByaNr(Integer aNr) {
        accountRepository.deleteByaNr(aNr);
    }
}
