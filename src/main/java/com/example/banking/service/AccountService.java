package com.example.banking.service;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    CustomerRepository customerRepository =  CustomerRepository.getCustomerRepository();
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
            accountRepository.save(arequest);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }

    }




    public void deleteByaNr(Integer aNr) {
        accountRepository.deleteByaNr(aNr);
    }
}
