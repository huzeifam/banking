package com.example.banking.service;

import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService  {

   private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponse> findAll() {

        return customerRepository.findAll();

    }

    public Optional<CustomerResponse> findByCustomerNo(Integer customerNo) {
        return customerRepository.findById(customerNo);
    }

    public CustomerResponse save(CustomerResponse request) {
        return customerRepository.save(request);
    }

    public void deleteByCustomerNo(Integer customerNo) {
        customerRepository.deleteById(customerNo);
    }
}


