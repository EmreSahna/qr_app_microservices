package com.emresahna.customerservice.repository;

import com.emresahna.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String>{
    Customer findByEmailAndPassword(String customer_name, String password);
}
