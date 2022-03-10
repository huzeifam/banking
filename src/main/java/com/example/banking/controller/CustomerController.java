package com.example.banking.controller;


import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {

    CustomerRepository customerRepository = new CustomerRepository();

    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers(){
        return customerRepository.findAll();
    }

    /*@GetMapping("/customers/{kNr}")*/

    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(
            @RequestBody CustomerCreateRequest request
    ){
        return customerRepository.save(
                request
        );
    }
    @DeleteMapping("/customers/{kNr}")
    public ResponseEntity deleteCustomer(
            @PathVariable Integer kNr
    ){
        customerRepository.deleteBykNr(kNr);
        return ResponseEntity.noContent().build();
    }
}
