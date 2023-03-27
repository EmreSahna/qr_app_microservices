package com.emresahna.userservice.repository;

import com.emresahna.userservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByEmailAndPassword(String customer_name, String password);
}
