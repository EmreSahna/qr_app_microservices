package com.emresahna.customerservice.service;

import com.emresahna.customerservice.dto.CustomerRequest;
import com.emresahna.customerservice.entity.Customer;
import com.emresahna.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public Customer createCustomer(CustomerRequest customer) {
        return customerRepository.save(Customer.builder()
                .customerName(customer.getCustomer_name())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .bankDetails(customer.getBank_details())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build());
    }
}
