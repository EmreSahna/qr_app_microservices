package com.emresahna.userservice.service;

import com.emresahna.userservice.dto.LoginRequest;
import com.emresahna.userservice.dto.RegisterCustomer;
import com.emresahna.userservice.entity.Customer;
import com.emresahna.userservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public Customer registerCustomer(RegisterCustomer customer) {
        return customerRepository.save(Customer.builder()
                .customerName(customer.getCustomer_name())
                .password(customer.getPassword())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .bankDetails(customer.getBank_details())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    public Customer loginCustomer(LoginRequest customer) {
        return customerRepository.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
    }

    public Customer getCustomer(String customer_id) {
        return customerRepository.findById(customer_id).orElse(null);
    }
}
