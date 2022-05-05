package com.example.customer.service;

import com.example.customer.model.AllTimeCustomers;
import com.example.customer.model.CustomerResponse;
import com.example.customer.repository.AllTimeCustomersRepository;
import com.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService  {

   private final CustomerRepository customerRepository;
   private final AllTimeCustomersRepository allTimeCustomersRepository;

    public CustomerService(CustomerRepository customerRepository, AllTimeCustomersRepository allTimeCustomersRepository) {
        this.customerRepository = customerRepository;
        this.allTimeCustomersRepository = allTimeCustomersRepository;
    }

    public List<CustomerResponse> findAll() {

        return customerRepository.findAll();

    }

    public Optional<CustomerResponse> findByCustomerNo(Integer customerNo) {
        return customerRepository.findById(customerNo);
    }

    public CustomerResponse save(CustomerResponse request) {
        return customerRepository.save(request);
    }

    public void deleteByCustomerNo(Integer customerNo) {
        customerRepository.deleteById(customerNo);
    }

    public List<Integer> getCustomerNo() {
        return customerRepository.findAllCustomerNo();
    }

    public String getCustomerFirstName(Integer customerNo) {
        return customerRepository.findCustomerFirstName(customerNo);
    }

    public String getCustomerLastName(Integer customerNo) {
        return customerRepository.findCustomerLastName(customerNo);
    }
    public LocalDate getCustomerBirthDate(Integer customerNo) {
        return customerRepository.findCustomerBirthDate(customerNo);
    }

    public List<AllTimeCustomers> findofAllTime() {
        return allTimeCustomersRepository.findAll();
    }

//    Archiv

    public AllTimeCustomers addToArchive(AllTimeCustomers allTimeCustomers) {
        return allTimeCustomersRepository.save(allTimeCustomers);
    }

    public Optional<AllTimeCustomers> findInArchiveByCustomerNo(Integer customerNo) {
        return allTimeCustomersRepository.findById(customerNo);
    }
}


