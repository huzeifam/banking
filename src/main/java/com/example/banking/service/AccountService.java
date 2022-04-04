package com.example.banking.service;

import com.example.banking.model.AccountResponse;
import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

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

    public Optional<AccountResponse> findByAccountNo(Integer accountNo) {

        return accountRepository.findById(accountNo);
    }

    public List<AccountResponse> findAccountByCustomerNo(Integer customerNo) {

        return accountRepository.findAccountByCustomerNo(customerNo);
    }


    public AccountResponse createAccount(AccountResponse accountNo) {
        Optional<CustomerResponse> customer = customerRepository.findById(accountNo.getCustomerNo());

        if (customer.isPresent()) {
            return accountRepository.save(accountNo);

        } else {
            return null;
        }

    }


    public void deleteAccountByCustomerNo(Integer customerNo) {

        accountRepository.deleteAccountByCustomerNo(customerNo);

    }

    public void deleteByAccountNo(Integer accountNo) {

        accountRepository.deleteById(accountNo);
    }

    public double getBalanceInEuro(Integer accountNo) {
        return accountRepository.findBalanceByAccountNo(accountNo);
    }


    public void depositAmount(Integer accountNo, Double amount) {
        accountRepository.saveBalanceByAccountNo(accountNo, amount);
    }

    public void withdrawAmount(Integer accountNo, Double amount) {
        accountRepository.withdrawAmountByAccountNo(accountNo, amount);
    }


    public void transferAmount(Integer accountNo, Integer destAccountNo, Double amount) {
        accountRepository.withdrawAmountByAccountNo(accountNo, amount);
        accountRepository.saveBalanceByAccountNo(destAccountNo, amount);

    }
}
