package com.example.banking.service;

import com.example.banking.model.AccountResponse;
import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }


    public List<AccountResponse> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<AccountResponse> findByANr(Integer aNr) {

        return accountRepository.findById(aNr);
    }

    public List<AccountResponse> findAccountByKNr(Integer kNr) {

        return accountRepository.findAccountByKNr(kNr);
    }


    public AccountResponse createAccount(AccountResponse aNr) {
        Optional<CustomerResponse> customer = customerRepository.findById(aNr.getkNr());

        if (customer.isPresent()) {
            return accountRepository.save(aNr);

        } else {
            return null;
        }

    }


    public void deleteAccountByKNr(Integer kNr) {

        accountRepository.deleteAccountByKNr(kNr);

    }

    public void deleteByaNr(Integer aNr) {

        accountRepository.deleteById(aNr);
    }

    public double getBalanceInEuro(Integer aNr) {
        return accountRepository.findBalanceByANr(aNr);
    }


    public void depositAmount(Integer aNr, Double amount) {
        accountRepository.saveBalanceByANr(aNr, amount);
    }

    public void withdrawAmount(Integer aNr, Double amount) {
        accountRepository.withdrawAmountByANr(aNr, amount);
    }


    public void transferAmount(Integer aNr, Integer newANr, Double amount) {
        accountRepository.withdrawAmountByANr(aNr, amount);
        accountRepository.saveBalanceByANr(newANr, amount);

    }
}
