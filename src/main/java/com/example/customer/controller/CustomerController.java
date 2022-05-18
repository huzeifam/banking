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
    @Operation(summary = "Get all customers in archive")
    @GetMapping("/archive")
    public List<AllTimeCustomers> getCustomersOfAllTime() {
        return customerService.findofAllTime();
    }

    @Operation(summary = "Find a customer in archive by customer number")
    @GetMapping("/archive/{customerNo}")
    public ResponseEntity<Object> getCustomerInArchiveByCustomerNo(
            @Parameter(description = "Customer number of customer to be found")
            @PathVariable Integer customerNo
    ) {
        Optional<AllTimeCustomers> customer = customerService.findInArchiveByCustomerNo(customerNo);
        if (customer.isPresent())
            return ResponseEntity.ok(customer.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer (customer number: " + customerNo + ") not found.");
    }

    @Operation(summary = "Find all customers in archive with search word")
    @GetMapping("/archive/search/{word}")
    public Object[] getCustomersInArchiveByWord(
            @Parameter(description = "Search parameter")
            @RequestParam CustomerSearchEnum parameter,
            @Parameter(description = "Search word")
            @PathVariable String word
    ) {
        List<AllTimeCustomers> search = customerService.findofAllTime();
        String notFound = (word+" in parameter "+parameter+" not found");
//        String notFoundMale = ("No Customer with gender (male)");
//        String notFoundFemale = ("No Customer with gender (female)");
//        String notFoundOthers = ("No Customer with gender (others)");
        List<AllTimeCustomers> filterdList = new ArrayList<>();

        for (int i = 0; i < search.size(); i++) {
            if (parameter.getParameter().matches("lastName")) {
                String searchWord = word.substring(0,1).toUpperCase();
                searchWord += word.substring(1);
                String finalSearchWord = searchWord;
                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getLastName().equals(finalSearchWord)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            } else if (parameter.getParameter().matches("firstName")) {
                String searchWord = word.substring(0,1).toUpperCase();
                searchWord += word.substring(1);
                String finalSearchWord = searchWord;
                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getFirstName().equals(finalSearchWord)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            }
//            else if (parameter.getParameter().matches("sex-male")){
//                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getSex().equals("Male")).collect(Collectors.toList());
//                if (filterdList.isEmpty())
//                    return new String[]{notFoundMale};
//                else
//                    return filterdList.toArray();
//            }
//            else if (parameter.getParameter().matches("sex-female")){
//                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getSex().equals("Female")).collect(Collectors.toList());
//                if (filterdList.isEmpty())
//                    return new String[]{notFoundFemale};
//                else
//                    return filterdList.toArray();
//            }
//            else if (parameter.getParameter().matches("sex-others")){
//                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getSex().equals("Others")).collect(Collectors.toList());
//                if (filterdList.isEmpty())
//                    return new String[]{notFoundOthers};
//                else
//                    return filterdList.toArray();
//            }
            else if (parameter.getParameter().matches("idCardNo")) {
                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getIdCardNo().equals(word.toUpperCase())).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            } else if (parameter.getParameter().matches("city")) {
                String searchWord = word.substring(0,1).toUpperCase();
                searchWord += word.substring(1);
                String finalSearchWord = searchWord;
                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getCity().equals(finalSearchWord)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            } else if (parameter.getParameter().matches("country")) {
                String searchWord = word.substring(0,1).toUpperCase();
                searchWord += word.substring(1);
                String finalSearchWord = searchWord;
                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getCountry().equals(finalSearchWord)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            }
        }
        return new String[]{notFound};
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer (customer number: " + customerNo + ") not found.");
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
        String notFound = (word+" in parameter "+parameter+" not found");
//        String notFoundMale = ("No Customer with gender (male)");
//        String notFoundFemale = ("No Customer with gender (female)");
//        String notFoundOthers = ("No Customer with gender (others)");
        List<CustomerResponse> filterdList = new ArrayList<>();
        int i;
        for (i = 0; i < search.size(); i++) {
            if (parameter.getParameter().matches("lastName")) {
                String searchWord = word.substring(0,1).toUpperCase();
                searchWord += word.substring(1);
                String finalSearchWord = searchWord;
                filterdList = search.stream().filter(customerResponse -> customerResponse.getLastName().equals(finalSearchWord)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            } else if (parameter.getParameter().matches("firstName")) {
                String searchWord = word.substring(0,1).toUpperCase();
                searchWord += word.substring(1);
                String finalSearchWord = searchWord;
                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getFirstName().equals(finalSearchWord)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            }
//            else if (parameter.getParameter().matches("sex-male")){
//                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getSex().equals("Male")).collect(Collectors.toList());
//                if (filterdList.isEmpty())
//                    return new String[]{notFoundMale};
//                else
//                    return filterdList.toArray();
//            }
//            else if (parameter.getParameter().matches("sex-female")){
//                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getSex().equals("Female")).collect(Collectors.toList());
//                if (filterdList.isEmpty())
//                    return new String[]{notFoundFemale};
//                else
//                    return filterdList.toArray();
//            }
//            else if (parameter.getParameter().matches("sex-others")){
//                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getSex().equals("Others")).collect(Collectors.toList());
//                if (filterdList.isEmpty())
//                    return new String[]{notFoundOthers};
//                else
//                    return filterdList.toArray();
//            }
            else if (parameter.getParameter().matches("idCardNo")) {
                filterdList = search.stream().filter(allTimeCustomers -> allTimeCustomers.getIdCardNo().equals(word.toUpperCase())).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            } else if (parameter.getParameter().matches("city")) {
                String searchWord = word.substring(0,1).toUpperCase();
                searchWord += word.substring(1);
                String finalSearchWord = searchWord;
                filterdList = search.stream().filter(customerResponse -> customerResponse.getCity().equals(finalSearchWord)).collect(Collectors.toList());
                if (filterdList.isEmpty())
                    return new String[]{notFound};
                else
                    return filterdList.toArray();
            } else if (parameter.getParameter().matches("country")) {
                String searchWord = word.substring(0,1).toUpperCase();
                searchWord += word.substring(1);
                String finalSearchWord = searchWord;
                filterdList = search.stream().filter(customerResponse -> customerResponse.getCountry().equals(finalSearchWord)).collect(Collectors.toList());
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
            @RequestParam CustomerSexEnum gender,
            @Parameter(description = "Select type of email")
            @RequestParam EmailTypeEnum email,
            @Parameter(description = "Select academicTitle")
            @RequestParam AcademicTitleEnum academicTitle,
            @Parameter(description = "Select type of telephone number")
            @RequestParam TelephonetypeEnum telephonetype
    ) {
        CustomerResponse customer = null;
        if (customerService.findByCustomerNo(customerNo).isPresent()) {
            customer = customerService.findByCustomerNo(customerNo).orElseThrow();
            AllTimeCustomers allTimeCustomers = customerService.findInArchiveByCustomerNo(customerNo).orElseThrow();


            if (request.getIdCardNo() != null) {
                customer.setIdCardNo(request.getIdCardNo());
                allTimeCustomers.setIdCardNo(request.getIdCardNo());
            }
            if (request.getNationality() != null) {
                customer.setNationality(request.getNationality());
                allTimeCustomers.setNationality(request.getNationality());
            }
            if (request.getBirthDate() != null) {
                customer.setBirthDate(request.getBirthDate());
                allTimeCustomers.setBirthDate(request.getBirthDate());
            }
            if (academicTitle.getAcademicTitle() != null) {
                customer.setAcademicTitle(academicTitle.getAcademicTitle());
                allTimeCustomers.setAcademicTitle(academicTitle.getAcademicTitle());
            }
            if (request.getFirstName() != null) {
                customer.setFirstName(request.getFirstName());
                allTimeCustomers.setFirstName(request.getFirstName());
            }
            if (request.getLastName() != null) {
                customer.setLastName(request.getLastName());
                allTimeCustomers.setLastName(request.getLastName());
            }
            if (gender.getSex() != null) {
                customer.setSex(gender.getSex());
                allTimeCustomers.setSex(gender.getSex());
            }
            if (request.getEmail() != null) {
                customer.setEmail(request.getEmail());
                allTimeCustomers.setEmail(request.getEmail());
            }
            if (email.getEmailType() != null) {
                customer.setEmailType(email.getEmailType());
                allTimeCustomers.setEmailType(email.getEmailType());
            }
            if (request.getTelephone() != null) {
                customer.setTelephone(request.getTelephone());
                allTimeCustomers.setTelephone(request.getTelephone());
            }
            if (telephonetype.getTelephoneNumberType() != null) {
                customer.setTelephoneNumberType(telephonetype.getTelephoneNumberType());
                allTimeCustomers.setTelephoneNumberType(telephonetype.getTelephoneNumberType());
            }
            if (request.getStreet() != null) {
                customer.setStreet(request.getStreet());
                allTimeCustomers.setStreet(request.getStreet());
            }
            if (request.getStreetNo() != null) {
                customer.setStreetNo(request.getStreetNo());
                allTimeCustomers.setStreetNo(request.getStreetNo());
            }
            if (request.getZipCode() != null) {
                customer.setZipCode(request.getZipCode());
                allTimeCustomers.setZipCode(request.getZipCode());
            }
            if (request.getCity() != null) {
                customer.setCity(request.getCity());
                allTimeCustomers.setCity(request.getCity());
            }
            if (request.getCountry() != null) {
                customer.setCountry(request.getCountry());
                allTimeCustomers.setCountry(request.getCountry());
            }

            customer.setHasOnlineBanking(request.isHasOnlineBanking());
            allTimeCustomers.setHasOnlineBanking(request.isHasOnlineBanking());

            customer.setInvesting(request.isInvesting());
            allTimeCustomers.setInvesting(request.isInvesting());

            customer.setNaturalPerson(request.isNaturalPerson());
            allTimeCustomers.setNaturalPerson(request.isNaturalPerson());

            customer.setHasAnotherBank(request.isHasAnotherBank());
            allTimeCustomers.setHasAnotherBank(request.isHasAnotherBank());

            customer.setSaving(request.isSaving());
            allTimeCustomers.setSaving(request.isSaving());

            customer.setCreditWorthy(request.isCreditWorthy());
            allTimeCustomers.setCreditWorthy(request.isCreditWorthy());
            customerService.addToArchive(allTimeCustomers);
            CustomerResponse savedCustomer = customerService.save(customer);
            return mapToResponse(savedCustomer);
        }
        return  null;
    }

    @Operation(summary = "Create a customer")
    @PostMapping("/customers")
    public CustomerResponse createCustomer(
            @RequestBody CustomerCreateRequest request,
            @Parameter(description = "Select gender")
            @RequestParam CustomerSexEnum gender,
            @Parameter(description = "Select type of email")
            @RequestParam EmailTypeEnum email,
            @Parameter(description = "Select academic title")
            @RequestParam AcademicTitleEnum academicTitle,
            @Parameter(description = "Select type of telephone number")
            @RequestParam TelephonetypeEnum telephonetype

    ) {
        Integer customerNo = UUID.randomUUID().hashCode() & Integer.MAX_VALUE;
        CustomerResponse response = new CustomerResponse(
                customerNo,
                request.getIdCardNo(),
                request.getNationality(),
                request.getBirthDate(),
                academicTitle.getAcademicTitle(),
                request.getFirstName(),
                request.getLastName(),
                gender.getSex(),
                request.getEmail(),
                email.getEmailType(),
                request.getTelephone(),
                telephonetype.getTelephoneNumberType(),
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
                request.getNationality(),
                request.getBirthDate(),
                academicTitle.getAcademicTitle(),
                request.getFirstName(),
                request.getLastName(),
                gender.getSex(),
                request.getEmail(),
                email.getEmailType(),
                request.getTelephone(),
                telephonetype.getTelephoneNumberType(),
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
        if (academicTitle.getAcademicTitle() == null) {
            savedCustomer.setAcademicTitle("-");
            archive.setAcademicTitle("-");
        }
        if (gender.getSex() == null) {
            savedCustomer.setSex("No gender specified!");
            archive.setSex("No gender specified!");
        }
        addToArchive(archive);
//

        return mapToResponse(savedCustomer);
    }

    private CustomerResponse mapToResponse(CustomerResponse savedCustomer) {
        return new CustomerResponse(
                savedCustomer.getCustomerNo(),
                savedCustomer.getIdCardNo(),
                savedCustomer.getNationality(),
                savedCustomer.getBirthDate(),
                savedCustomer.getAcademicTitle(),
                savedCustomer.getFirstName(),
                savedCustomer.getLastName(),
                savedCustomer.getSex(),
                savedCustomer.getEmail(),
                savedCustomer.getEmailType(),
                savedCustomer.getTelephone(),
                savedCustomer.getTelephoneNumberType(),
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
//                                Double totalBalance = restTemplate.getForObject("http://account:8085/api/accounts/"+customerNo+"/totalbalance", Double.class);
            if (totalBalance != null) {
                if (totalBalance > 0) {
                    restTemplate.delete("http://localhost:8085/api/accounts/customer-accounts/{customerNo}", customerNo);
//                    restTemplate.delete("http://account:8085/api/accounts/customer-accounts/{customerNo}", customerNo);

                    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Could not delete. At least one account still contains money. Please withdraw the remaining amount \"" + Math.round(totalBalance*100.0)/100.0 + "€\" and try again.\n" +
                            "The remaining accounts of customer (customer number: " + customerNo + ", name: "+customer.get().getFirstName()+" "+customer.get().getLastName()+") with zero balance were deleted (except for accounts with ongoing credits).");
                } else {
                    restTemplate.delete("http://localhost:8085/api/accounts/customer-accounts/{customerNo}", customerNo);
//                restTemplate.delete("http://account:8085/api/accounts/customer-accounts/{customerNo}",customerNo);
                    List customerAccounts = restTemplate.getForObject("http://localhost:8085/api/accounts/customer-accounts/" + customerNo, List.class);
//                    List customerAccounts = restTemplate.getForObject("http://account:8085/api/accounts/customer-accounts/" + customerNo, List.class);

                    if (customerAccounts == null) {
                        customerService.deleteByCustomerNo(customerNo);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer (customer number: " + customerNo + ", name: "+customer.get().getFirstName()+" "+customer.get().getLastName()+") and related accounts deleted.");
                    } else {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body("Could not delete customer. Possible reasons: Customer (customer number: " + customerNo + ", name: "+customer.get().getFirstName()+" "+customer.get().getLastName()+") -\n" +
                                "-still has at least one account which contains money.\n" +
                                "-still has at least one account with an ongoing credit.\n\n" +
                                "All accounts of customer with zero balance and zero ongoing credits were deleted.\n" +
                                "Please check first and try again.");
                    }
                }


            } else {
                customerService.deleteByCustomerNo(customerNo);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer (customer number: " + customerNo + ", name: "+customer.get().getFirstName()+" "+customer.get().getLastName()+") deleted.");
            }
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete. Customer (customer number: " + customerNo + ") does not exist.");
    }
}




