package com.example.banking.repository;

import com.example.banking.model.CustomerCreateRequest;
import com.example.banking.model.CustomerResponse;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


}
