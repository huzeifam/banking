package com.example.banking.controller;


//import com.example.banking.model.AccountCreateRequest;
//import com.example.banking.model.AccountResponse;
//import com.example.banking.service.AccountService;

import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import com.example.banking.service.CustomerService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CustomerController {


//    private final AccountService accountService;
    private final CustomerService customerService;



    public CustomerController(/*AccountService accountService,*/ CustomerService customerService) {

//        this.accountService = accountService;
        this.customerService = customerService;
    }

    @Autowired
    RestTemplate restTemplate;

    @Operation(summary = "Get all customers")
    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers() {
        return customerService.findAll();
    }


    @Operation(summary = "Find a customer by customer number")
    @GetMapping("/customers/{customerNo}")
    public ResponseEntity<Object> getCustomerByCustomerNo(
            @Parameter(description = "Customer number of customer to be found")
            @PathVariable Integer customerNo
    ) {
        Optional<CustomerResponse> customer = customerService.findByCustomerNo(customerNo);
        if (customer.isPresent())
            return ResponseEntity.ok(customer.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with customer number " + customerNo + " not found.");
    }

    @Hidden
    @GetMapping("/customers/numbers")
    public List<Integer> getAllCustomerNo(){
        return customerService.getCustomerNo();
    }

    @Hidden
    @GetMapping("/customers/{customerNo}/first-name")
    public ResponseEntity<String> getCustomerFirstName(
            @PathVariable Integer customerNo
    ){
        Optional<CustomerResponse> customer = customerService.findByCustomerNo(customerNo);
        if (customer.isPresent())
            return customerService.getCustomerFirstName(customerNo);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with customer number "+customerNo+" does not exist.");
    }
    @Hidden
    @GetMapping("/customers/{customerNo}/last-name")
    public ResponseEntity<String> getCustomerLastName(
            @PathVariable Integer customerNo
    ){
        Optional<CustomerResponse> customer = customerService.findByCustomerNo(customerNo);
        if (customer.isPresent())
            return customerService.getCustomerLastName(customerNo);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with customer number "+customerNo+" does not exist.");
    }

    @Operation(summary = "Update a customer")
    @PutMapping("/customers/{customerNo}")
    public CustomerResponse updateCustomer(
            @Parameter(description = "Customer number of customer to update")
            @PathVariable Integer customerNo,
            @RequestBody CustomerCreateRequest request
    )  {

        CustomerResponse customer = customerService.findByCustomerNo(customerNo).orElseThrow();


        if (request.getIdCardNo() != null)
            customer.setIdCardNo(request.getIdCardNo());

        if (request.getBirthDate() != null)
            customer.setBirthDate(request.getBirthDate());

        if (request.getFirstName() != null)
            customer.setFirstName(request.getFirstName());

        if (request.getLastName() != null)
            customer.setLastName(request.getLastName());

        if (request.getStreet() != null)
            customer.setStreet(request.getStreet());

        if (request.getStreetNo() != null)
            customer.setStreetNo(request.getStreetNo());

        if (request.getCity() != null)
            customer.setCity(request.getCity());

    CustomerResponse savedCustomer = customerService.save(customer);
        return mapToResponse(savedCustomer);
    }

    @Operation(summary = "Create a customer")
    @PostMapping("/customers")
    public CustomerResponse createCustomer(
            @RequestBody CustomerCreateRequest request

    ) {
        CustomerResponse response = new CustomerResponse(
                UUID.randomUUID().hashCode() & Integer.MAX_VALUE,
                request.getIdCardNo(),
                request.getBirthDate(),
                request.getFirstName(),
                request.getLastName(),
                request.getStreet(),
                request.getStreetNo(),
                request.getCity()


        );

        CustomerResponse savedCustomer = customerService.save(response);


        return mapToResponse(savedCustomer);
    }

    private CustomerResponse mapToResponse(CustomerResponse savedCustomer) {
        return new CustomerResponse(
                savedCustomer.getCustomerNo(),
                savedCustomer.getIdCardNo(),
                savedCustomer.getBirthDate(),
                savedCustomer.getFirstName(),
                savedCustomer.getLastName(),
                savedCustomer.getStreet(),
                savedCustomer.getStreetNo(),
                savedCustomer.getCity()
        );
    }

    @Operation(summary = "Delete a customer")
    @DeleteMapping("/customers/{customerNo}")
    public ResponseEntity deleteCustomer(
            @Parameter(description = "Customer number of customer to delete")
            @PathVariable Integer customerNo


    ) {
        Optional<CustomerResponse> customer = customerService.findByCustomerNo(customerNo);
        {

            if (customer.isPresent()) {
                customerService.deleteByCustomerNo(customerNo);
//              accountService.deleteAccountByCustomerNo(customerNo);
              restTemplate.delete("http://localhost:8085/api/accounts/customer-accounts/{customerNo}",customerNo);
//                restTemplate.delete("http://account:8085/api/accounts/customer-accounts/{customerNo}",customerNo);




                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer with customer number " + customerNo + " and related accounts deleted.");
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Customer with customer number " + customerNo + " does not exist.");
        }
    }
}
