package com.example.banking.repository;

import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;


public class CustomerRepository {

    private static CustomerRepository theRepository;
    public static CustomerRepository getCustomerRepository(){
        if (theRepository == null){
            theRepository = new CustomerRepository();
        }
        return theRepository;
    }
    List<CustomerResponse> customers = new ArrayList<>();


    public List<CustomerResponse> findAll() {
        return customers;
    }

    public Optional<CustomerResponse> findByKNr(Integer kNr) {
        Optional<CustomerResponse> customer = customers.stream()
                .filter(c -> c.getkNr().equals(kNr))
                .findFirst();
        return customer;
    }

//    public Optional<CustomerAccountResponse> findAccountByKNr(Integer kNr) {
//        Optional<CustomerAccountResponse> customerAccount = customers.stream()
//                .filter(c -> c.getkNr().equals(kNr))
//                .findFirst();
//        return customerAccount;
//
//    }
    public ResponseEntity<Object> save(CustomerCreateRequest request) {

        customers.add(
                new CustomerResponse(
                UUID.randomUUID().hashCode() & Integer.MAX_VALUE,
                request.getPassNr(),
                request.getGbDate(),
                request.getvName(),
                request.getnName(),
                request.getStraße(),
                request.gethNr(),
                request.getOrt(),
                Collections.emptyList())
                );
        return ResponseEntity.ok().build();
    }

    public void deleteBykNr(Integer kNr) {
        this.customers = customers.stream()
                .filter(p -> !p.getkNr().equals(kNr))
                .collect(Collectors.toList());

    }



}
