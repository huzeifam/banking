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
    public CustomerResponse updateCustomer(
            @PathVariable Integer kNr,
            @RequestBody CustomerCreateRequest request
    )  {

        CustomerResponse customer = customerService.findByKNr(kNr).orElseThrow();


        if (request.getPassNr() != null)
            customer.setPassNr(request.getPassNr());

        if (request.getGbDate() != null)
            customer.setGbDate(request.getGbDate());

        if (request.getvName() != null)
            customer.setvName(request.getvName());

        if (request.getnName() != null)
            customer.setnName(request.getnName());

        if (request.getStraße() != null)
            customer.setStraße(request.getStraße());

        if (request.gethNr() != null)
            customer.sethNr(request.gethNr());

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
                request.getvName(),
                request.getnName(),
                request.getStraße(),
                request.gethNr(),
                request.getOrt()


        );
        CustomerResponse savedCustomer = customerService.save(response);


        return mapToResponse(savedCustomer);
    }

    private CustomerResponse mapToResponse(CustomerResponse savedCustomer) {
        return new CustomerResponse(
                savedCustomer.getkNr(),
                savedCustomer.getPassNr(),
                savedCustomer.getGbDate(),
                savedCustomer.getvName(),
                savedCustomer.getnName(),
                savedCustomer.getStraße(),
                savedCustomer.gethNr(),
                savedCustomer.getOrt()
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
