package com.emresahna.customerservice.service;

import com.emresahna.customerservice.dto.LoginRequest;
import com.emresahna.customerservice.dto.RegisterRequest;
import com.emresahna.customerservice.entity.Customer;
import com.emresahna.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public Customer registerCustomer(RegisterRequest customer) {
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
