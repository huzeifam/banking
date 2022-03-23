package com.example.banking.service;

import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

   private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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
}


