package com.example.banking.controller;


import com.example.banking.model.AccountCreateRequest;
import com.example.banking.model.AccountResponse;
import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import com.example.banking.service.AccountService;
import com.example.banking.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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



    @GetMapping("/customers/{customerNo}")
    public ResponseEntity<Object> getCustomerByCustomerNo(
            @PathVariable Integer customerNo
    ) {
        Optional<CustomerResponse> customer = customerService.findByCustomerNo(customerNo);
        if (customer.isPresent())
            return ResponseEntity.ok(customer.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with customer number " + customerNo + " not found.");
    }


    @PutMapping("/customers/{customerNo}")
    public CustomerResponse updateCustomer(
            @PathVariable Integer customerNo,
            @RequestBody CustomerCreateRequest request
    )  {

        CustomerResponse customer = customerService.findByCustomerNo(customerNo).orElseThrow();


        if (request.getPassNr() != null)
            customer.setPassNr(request.getPassNr());

        if (request.getGbDate() != null)
            customer.setGbDate(request.getGbDate());

        if (request.getFirstName() != null)
            customer.setFirstName(request.getFirstName());

        if (request.getLastName() != null)
            customer.setLastName(request.getLastName());

        if (request.getStreet() != null)
            customer.setStreet(request.getStreet());

        if (request.getStreetNo() != null)
            customer.setStreetNo(request.getStreetNo());

        if (request.getOrt() != null)
            customer.setOrt(request.getOrt());

    CustomerResponse savedCustomer = customerService.save(customer);
        return mapToResponse(savedCustomer);
    }


    @PostMapping("/customers")
    public CustomerResponse createCustomer(
            @RequestBody CustomerCreateRequest request

    ) {
        CustomerResponse response = new CustomerResponse(
                UUID.randomUUID().hashCode() & Integer.MAX_VALUE,
                request.getPassNr(),
                request.getGbDate(),
                request.getFirstName(),
                request.getLastName(),
                request.getStreet(),
                request.getStreetNo(),
                request.getOrt()


        );

        CustomerResponse savedCustomer = customerService.save(response);


        return mapToResponse(savedCustomer);
    }

    private CustomerResponse mapToResponse(CustomerResponse savedCustomer) {
        return new CustomerResponse(
                savedCustomer.getCustomerNo(),
                savedCustomer.getPassNr(),
                savedCustomer.getGbDate(),
                savedCustomer.getFirstName(),
                savedCustomer.getLastName(),
                savedCustomer.getStreet(),
                savedCustomer.getStreetNo(),
                savedCustomer.getOrt()
        );
    }


    @DeleteMapping("/customers/{customerNo}")
    public ResponseEntity deleteCustomer(
            @PathVariable Integer customerNo

    ) {
        Optional<CustomerResponse> customer = customerService.findByCustomerNo(customerNo);
        {

            if (customer.isPresent()) {
                customerService.deleteByCustomerNo(customerNo);
                accountService.deleteAccountByCustomerNo(customerNo);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer with customer number " + customerNo + " and related accounts deleted.");
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Customer with customer number " + customerNo + " does not exist.");
        }
    }
}
