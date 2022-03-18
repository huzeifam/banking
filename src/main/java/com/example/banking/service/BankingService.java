package com.example.banking.service;

import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankingService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public BankingService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }



    public List<AccountResponse> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<AccountResponse> findByANr(Integer aNr) {

        return accountRepository.findByANr(aNr);
    }

    public List<AccountResponse> findAccountByKNr(Integer kNr) {

        return accountRepository.FindAccountByKNr(kNr);
    }


    public ResponseEntity<Object> createAccount(AccountCreateRequest arequest) {
        Optional<CustomerResponse> customer = customerRepository.findByKNr(arequest.getkNr());

        if (customer.isPresent()) {
            accountRepository.save(arequest);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not create account. Customer does not exist.");
        }

    }


    public void deleteAccountByKNr(Integer kNr){

        accountRepository.deleteAccountByKNr(kNr);
    }

    public void deleteByaNr(Integer aNr) {

        accountRepository.deleteByaNr(aNr);
    }

    public double getBalanceInEuro(Integer aNr) {
        return accountRepository.findBalanceByANr(aNr);
    }

    // customer

    public List<CustomerResponse> findAll() {

        return customerRepository.findAll();

    }

    public Optional<CustomerResponse> findByKNr(Integer kNr) {
        return customerRepository.findByKNr(kNr);
    }

    public ResponseEntity<Object> save(CustomerCreateRequest request) {
        return customerRepository.save(request);
    }

    public void deleteBykNr(Integer kNr) {
        customerRepository.deleteBykNr(kNr);
    }


    public void depositAmount(Integer aNr, Double amount) {
        accountRepository.saveBalanceByANr(aNr, amount);
    }

    public void withdrawAmount(Integer aNr, Double amount) {
        accountRepository.withdrawAmountByANr(aNr, amount);
    }


    public void transferAmount(Integer aNr, Integer newANr, Double amount) {
        accountRepository.transferAmountByANr(aNr, newANr, amount);

    }
}
