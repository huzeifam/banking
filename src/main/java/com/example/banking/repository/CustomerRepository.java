package com.example.banking.repository;

import com.example.banking.model.CustomerAccountResponse;
import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomerRepository {

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
                request.getOrt()
                )
                );
        return ResponseEntity.ok().build();
    }

    public void deleteBykNr(Integer kNr) {
        this.customers = customers.stream()
                .filter(p -> !p.getkNr().equals(kNr))
                .collect(Collectors.toList());

    }

    
}
