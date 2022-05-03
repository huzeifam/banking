package com.example.banking.controller;


//import com.example.banking.model.AccountCreateRequest;
//import com.example.banking.model.AccountResponse;
//import com.example.banking.service.AccountService;

import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import com.example.banking.model.CustomerSearchEnum;
import com.example.banking.model.CustomerSexEnum;
import com.example.banking.service.CustomerService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
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

    /*@Operation(summary = "Find all customers with search word")
    @GetMapping("/customers/{searchW}")
    public ResponseEntity<Object> getCustomerBySearch(
        @Parameter(description = "Search parameter")
        //@PathVariable String searchP,
        @RequestParam CustomerSearchEnum searchP,
        @Parameter(description = "Search word")
        @PathVariable String searchW
    ){
        Optional<CustomerResponse> search = customerService.getCustomerBySearch(searchW);
        if (!search.isEmpty())
            return ResponseEntity.ok(search.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find customer with matching criteria");
    }*/


    @Hidden
    @GetMapping("/customers/numbers")
    public List<Integer> getAllCustomerNo(){
        return customerService.getCustomerNo();
    }

    @Hidden
    @GetMapping("/customers/{customerNo}/first-name")
    public String getCustomerFirstName(
            @PathVariable Integer customerNo
    ){

            return customerService.getCustomerFirstName(customerNo);

    }
    @Hidden
    @GetMapping("/customers/{customerNo}/last-name")
    public String getCustomerLastName(
            @PathVariable Integer customerNo
    ){

            return customerService.getCustomerLastName(customerNo);
    }

    @Hidden
    @GetMapping("/customers/{customerNo}/age")
    public Integer getCustomerAge(
            @PathVariable Integer customerNo
    ){
        LocalDate birthDate = customerService.getCustomerBirthDate(customerNo);

        Integer age= LocalDate.now().getYear() - birthDate.getYear();

        if(LocalDate.now().getMonthValue() < birthDate.getMonthValue())  {
            return --age;
        } else if (LocalDate.now().getMonthValue() > birthDate.getMonthValue()) {
            return age;
        } else if (LocalDate.now().getDayOfMonth() < birthDate.getDayOfMonth()) {
            return --age;
        } else return age;}


    @Operation(summary = "Update a customer")
    @PutMapping("/customers/{customerNo}")
    public CustomerResponse updateCustomer(
            @Parameter(description = "Customer number of customer to update")
            @PathVariable Integer customerNo,
            @RequestBody CustomerCreateRequest request,
            @Parameter(description = "Select gender")
            @RequestParam CustomerSexEnum gender
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

        if (gender.getSex() != null)
            customer.setSex(gender.getSex());

        if (request.getEmail() != null)
            customer.setEmail(request.getEmail());

        if (request.getTelephone() != null)
            customer.setTelephone(request.getTelephone());

        if (request.getStreet() != null)
            customer.setStreet(request.getStreet());

        if (request.getStreetNo() != null)
            customer.setStreetNo(request.getStreetNo());

        if (request.getZipCode() != null)
            customer.setZipCode(request.getZipCode());

        if (request.getCity() != null)
            customer.setCity(request.getCity());

        if (request.getCountry() != null)
            customer.setCountry(request.getCountry());

    CustomerResponse savedCustomer = customerService.save(customer);
        return mapToResponse(savedCustomer);
    }

    @Operation(summary = "Create a customer")
    @PostMapping("/customers")
    public CustomerResponse createCustomer(
            @RequestBody CustomerCreateRequest request,
            @Parameter(description = "Select gender")
            @RequestParam CustomerSexEnum gender

    ) {
        CustomerResponse response = new CustomerResponse(
                UUID.randomUUID().hashCode() & Integer.MAX_VALUE,
                request.getIdCardNo(),
                request.getBirthDate(),
                request.getFirstName(),
                request.getLastName(),
                gender.getSex(),
                request.getEmail(),
                request.getTelephone(),
                request.getStreet(),
                request.getStreetNo(),
                request.getZipCode(),
                request.getCity(),
                request.getCountry()


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
                savedCustomer.getSex(),
                savedCustomer.getEmail(),
                savedCustomer.getTelephone(),
                savedCustomer.getStreet(),
                savedCustomer.getStreetNo(),
                savedCustomer.getZipCode(),
                savedCustomer.getCity(),
                savedCustomer.getCountry()
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
                Double totalBalance = restTemplate.getForObject("http://localhost:8085/api/accounts/"+customerNo+"/totalbalance", Double.class);
//                Double totalBalance = restTemplate.getForObject("http://account:8085/api/accounts/{customerNo}/totalbalance", Double.class);
                if (totalBalance != null) {
                    if (totalBalance > 0) {
                        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Could not delete. One or more Accounts still contain Money. Please withdraw the remaining Amount \"" + totalBalance + "€\" and try again");
                    } else
                        customerService.deleteByCustomerNo(customerNo);

                    restTemplate.delete("http://localhost:8085/api/accounts/customer-accounts/{customerNo}", customerNo);
//                restTemplate.delete("http://account:8085/api/accounts/customer-accounts/{customerNo}",customerNo);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer with customer number " + customerNo + " and related accounts deleted.");
                }else
                    customerService.deleteByCustomerNo(customerNo);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer with customer number " + customerNo + " deleted.");
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Customer with customer number " + customerNo + " does not exist.");
        }
    }

}
