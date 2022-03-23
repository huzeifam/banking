package com.example.banking.controller;


import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import com.example.banking.service.AccountService;
import com.example.banking.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {


    private final AccountService accountService;
    private final CustomerService customerService;


    public CustomerController(AccountService accountService, CustomerService customerService) {

        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers() {
        return customerService.findAll();
    }



    @GetMapping("/customers/{kNr}")
    public ResponseEntity<Object> getCustomerByKNr(
            @PathVariable Integer kNr
    ) {
        Optional<CustomerResponse> customer = customerService.findByKNr(kNr);
        if (customer.isPresent())
            return ResponseEntity.ok(customer.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with customer number " + kNr + " not found.");
    }


    @PutMapping("/customers/{kNr}")
    public ResponseEntity<Object> updateCustomer(
            @PathVariable Integer kNr,
            @RequestBody CustomerCreateRequest request
    ) {
        Optional<CustomerResponse> customer = customerService.findByKNr(kNr);
        if (customer.isPresent()) {


            if (request.getPassNr() != null)
                customer.get().setPassNr(request.getPassNr());

            if (request.getGbDate() != null)
                customer.get().setGbDate(request.getGbDate());

            if (request.getvName() != null)
                customer.get().setvName(request.getvName());

            if (request.getnName() != null)
                customer.get().setnName(request.getnName());

            if (request.getStraße() != null)
                customer.get().setStraße(request.getStraße());

            if (request.gethNr() != null)
                customer.get().sethNr(request.gethNr());

            if (request.getOrt() != null)
                customer.get().setOrt(request.getOrt());

            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with customer number " + kNr + " not found.");
        }
    }


    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(
            @RequestBody CustomerCreateRequest request

    ) {

        return customerService.save(
                request
        );
    }


    @DeleteMapping("/customers/{kNr}")
    public ResponseEntity deleteCustomer(
            @PathVariable Integer kNr,
            AccountCreateRequest aRequest
    ) {
        Optional<CustomerResponse> customer = customerService.findByKNr(kNr);
        {
            Optional<CustomerResponse> account = customerService.findByKNr(aRequest.getkNr());
            if (customer.isPresent()) {
                customerService.deleteBykNr(kNr);
                accountService.deleteAccountByKNr(aRequest.getkNr());

                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer with customer number " + kNr + " and related accounts deleted.");
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Customer with customer number " + kNr + " does not exist.");
        }
    }
}
