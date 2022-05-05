package com.example.customer.controller;


//import com.example.banking.model.AccountCreateRequest;
//import com.example.banking.model.AccountResponse;
//import com.example.banking.service.AccountService;

import com.example.customer.model.*;
import com.example.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CustomerController {


    //    private final AccountService accountService;
    private final CustomerService customerService;
    private List<CustomerResponse> search;


    public CustomerController(/*AccountService accountService,*/ CustomerService customerService) {

//        this.accountService = accountService;
        this.customerService = customerService;
    }

    @Autowired
    RestTemplate restTemplate;


    public AllTimeCustomers addToArchive(AllTimeCustomers allTimeCustomers) {
        return customerService.addToArchive(allTimeCustomers);
    }

    //    Archiv
    @GetMapping("/archive")
    public List<AllTimeCustomers> getCustomersOfAllTime() {
        return customerService.findofAllTime();
    }

    @GetMapping("/archive/{customerNo}")
    public ResponseEntity<Object> getCustomerInArchiveByCustomerNo(
            @PathVariable Integer customerNo
    ){
        Optional<AllTimeCustomers> customer = customerService.findInArchiveByCustomerNo(customerNo);
        if (customer.isPresent())
            return ResponseEntity.ok(customer.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with customer number " + customerNo + " not found.");
    }

//    ___________________________________________________________________


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


    @Operation(summary = "Find all customers with search word")
    @GetMapping("/customers/search/{word}")
    public Object[] getCustomersByWord(
            @Parameter(description = "Search parameter")
            @RequestParam CustomerSearchEnum parameter,
            @Parameter(description = "Search word")
            @PathVariable String word
    ) {
        List<CustomerResponse> search = customerService.findAll();
        String notFound = ("Could not find matching word in parameter");
        List<CustomerResponse> filterdList = new ArrayList<>();
        int i;
        for (i = 0; i < search.size(); i++) {
            if (parameter.getParameter().matches("lastName")) {
                filterdList = search.stream().filter(customerResponse -> customerResponse.getLastName().equals(word)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            } else if (parameter.getParameter().matches("city")) {
                filterdList = search.stream().filter(customerResponse -> customerResponse.getCity().equals(word)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            } else if (parameter.getParameter().matches("country")) {
                filterdList = search.stream().filter(customerResponse -> customerResponse.getCountry().equals(word)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            }
        }
        return new String[]{notFound};
    }


    @Hidden
    @GetMapping("/customers/numbers")
    public List<Integer> getAllCustomerNo() {
        return customerService.getCustomerNo();
    }

    @Hidden
    @GetMapping("/customers/{customerNo}/first-name")
    public String getCustomerFirstName(
            @PathVariable Integer customerNo
    ) {

        return customerService.getCustomerFirstName(customerNo);

    }

    @Hidden
    @GetMapping("/customers/{customerNo}/last-name")
    public String getCustomerLastName(
            @PathVariable Integer customerNo
    ) {

        return customerService.getCustomerLastName(customerNo);
    }

    @Hidden
    @GetMapping("/customers/{customerNo}/age")
    public Integer getCustomerAge(
            @PathVariable Integer customerNo
    ) {
        Optional<CustomerResponse> customer = customerService.findByCustomerNo(customerNo);
        if (customer.isPresent()) {
            LocalDate birthDate = customerService.getCustomerBirthDate(customerNo);
            Integer age = LocalDate.now().getYear() - birthDate.getYear();

            if (LocalDate.now().getMonthValue() < birthDate.getMonthValue()) {
                return --age;
            } else if (LocalDate.now().getMonthValue() > birthDate.getMonthValue()) {
                return age;
            } else if (LocalDate.now().getDayOfMonth() < birthDate.getDayOfMonth()) {
                return --age;
            } else return age;
        } else
            return null;
    }


    @Operation(summary = "Update a customer")
    @PutMapping("/customers/{customerNo}")
    public CustomerResponse updateCustomer(
            @Parameter(description = "Customer number of customer to update")
            @PathVariable Integer customerNo,
            @RequestBody CustomerCreateRequest request,
            @Parameter(description = "Select gender")
            @RequestParam CustomerSexEnum gender
    ) {

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

        customer.setHasOnlineBanking(request.isHasOnlineBanking());
        customer.setInvesting(request.isInvesting());
        customer.setNaturalPerson(request.isNaturalPerson());
        customer.setHasAnotherBank(request.isHasAnotherBank());
        customer.setSaving(request.isSaving());
        customer.setCreditWorthy(request.isCreditWorthy());

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
        Integer customerNo = UUID.randomUUID().hashCode() & Integer.MAX_VALUE;
        CustomerResponse response = new CustomerResponse(
                customerNo,
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
                request.getCountry(),
                request.isHasOnlineBanking(),
                request.isInvesting(),
                request.isNaturalPerson(),
                request.isHasAnotherBank(),
                request.isSaving(),
                request.isCreditWorthy()


        );


        CustomerResponse savedCustomer = customerService.save(response);

//        Für das Archiv
        AllTimeCustomers archive = new AllTimeCustomers(
                customerNo,
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
                request.getCountry(),
                request.isHasOnlineBanking(),
                request.isInvesting(),
                request.isNaturalPerson(),
                request.isHasAnotherBank(),
                request.isSaving(),
                request.isCreditWorthy()


        );
        addToArchive(archive);
//
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
                savedCustomer.getCountry(),
                savedCustomer.isHasOnlineBanking(),
                savedCustomer.isInvesting(),
                savedCustomer.isNaturalPerson(),
                savedCustomer.isHasAnotherBank(),
                savedCustomer.isSaving(),
                savedCustomer.isCreditWorthy()
        );
    }

    @Operation(summary = "Delete a customer")
    @DeleteMapping("/customers/{customerNo}")
    public ResponseEntity<String> deleteCustomer(
            @Parameter(description = "Customer number of customer to delete")
            @PathVariable Integer customerNo
    ) {
        Optional<CustomerResponse> customer = customerService.findByCustomerNo(customerNo);

        if (customer.isPresent()) {
            Double totalBalance = restTemplate.getForObject("http://localhost:8085/api/accounts/" + customerNo + "/totalbalance", Double.class);
//                Double totalBalance = restTemplate.getForObject("http://account:8085/api/accounts/{customerNo}/totalbalance", Double.class);
            if (totalBalance != null) {
                if (totalBalance > 0) {
                    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Could not delete. At least one account still contains money. Please withdraw the remaining amount \"" + totalBalance + "€\" and try again.");
                } else {
                    restTemplate.delete("http://localhost:8085/api/accounts/customer-accounts/{customerNo}", customerNo);
//                restTemplate.delete("http://account:8085/api/accounts/customer-accounts/{customerNo}",customerNo);
                }
                List customerAccounts = restTemplate.getForObject("http://localhost:8085/api/accounts/customer-accounts/" + customerNo, List.class);
//                    List customerAccounts = restTemplate.getForObject("http://account:8085/api/accounts/customer-accounts/" + customerNo, List.class);

                if (customerAccounts.isEmpty()) {
                    customerService.deleteByCustomerNo(customerNo);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer with customer number " + customerNo + " and related accounts deleted.");
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Could not delete customer. Possible reasons: Customer (" + customerNo + ") -\n" +
                            "-still has at least one account which contains money.\n" +
                            "-still has at least one account with an ongoing credit.\n\n" +
                            "All accounts of customer with zero balance and zero ongoing credits were deleted.\n" +
                            "Please check first and try again.");
                }
            } else {
                customerService.deleteByCustomerNo(customerNo);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer with customer number " + customerNo + " deleted.");
            }
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Customer with customer number " + customerNo + " does not exist.");
    }
}




