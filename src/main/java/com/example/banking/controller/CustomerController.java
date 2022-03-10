package com.example.banking.controller;


import com.example.banking.model.CustomerAccountResponse;
import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.service.CustomerAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    CustomerRepository customerRepository = new CustomerRepository();
    CustomerAccountService customerAccountService = new CustomerAccountService();

    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{kNr}")
    public ResponseEntity<Object> getCustomerByKNr(
            @PathVariable Integer kNr
    ){
        Optional<CustomerResponse> customer = customerRepository.findByKNr(kNr);
        if (customer.isPresent())
            return ResponseEntity.ok(customer.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/customers/kNr/customerAcc")
    public ResponseEntity<Object> getAccountByKNr(
            @PathVariable Integer kNr
    ){return customerAccountService.getAccountsForCustomer(kNr);
        /*Optional<CustomerAccountResponse> customerAccount = customerRepository.findAccountByKNr(kNr);
        if (customerAccount.isPresent())
            return ResponseEntity.ok(customerAccount.get());
        else
            return ResponseEntity.notFound().build();*/
    }


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
