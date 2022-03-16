package com.example.banking.controller;


import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.service.BankingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final BankingService bankingService;



    public CustomerController(CustomerRepository customerRepository, BankingService bankingService) {
        this.customerRepository = customerRepository;
        this.bankingService = bankingService;
    }

    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers(){
        return bankingService.findAll();
    }

    @GetMapping("/customers/{kNr}")
    public ResponseEntity<Object> getCustomerByKNr(
            @PathVariable Integer kNr
    ){
        Optional<CustomerResponse> customer = bankingService.findByKNr(kNr);
        if (customer.isPresent())
            return ResponseEntity.ok(customer.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with customer number " + kNr + " not found");
    }

//    @GetMapping("/customers/kNr/customerAcc")
//    public ResponseEntity<Object> getAccountByKNr(
//            @PathVariable Integer kNr
//    ){return customerAccountService.getAccountsForCustomer(kNr);
//        /*Optional<CustomerAccountResponse> customerAccount = customerRepository.findAccountByKNr(kNr);
//        if (customerAccount.isPresent())
//            return ResponseEntity.ok(customerAccount.get());
//        else
//            return ResponseEntity.notFound().build();*/
//    }


    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(
            @RequestBody CustomerCreateRequest request

    ){

        return bankingService.save(
                request
        );
    }
    @DeleteMapping("/customers/{kNr}")
    public ResponseEntity deleteCustomer(
            @PathVariable Integer kNr,
            AccountCreateRequest arequest
    ){Optional<CustomerResponse> customer = bankingService.findByKNr(kNr);
        {Optional<CustomerResponse> account = bankingService.findByKNr(arequest.getkNr());
        if (customer.isPresent()) {
            bankingService.deleteBykNr(kNr);
           bankingService.deleteAccountByKNr(arequest.getkNr());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer with customer number " + kNr + " and related accounts deleted");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Customer with customer number " + kNr + " does not exist.");
    }
}}
