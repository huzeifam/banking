package com.example.customer.repository;

import com.example.customer.model.AllTimeCustomers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllTimeCustomersRepository extends JpaRepository<AllTimeCustomers, Integer> {
}
