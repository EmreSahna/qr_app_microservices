package com.emresahna.customerservice.controller;

import com.emresahna.customerservice.dto.LoginRequest;
import com.emresahna.customerservice.dto.RegisterRequest;
import com.emresahna.customerservice.entity.Customer;
import com.emresahna.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> createCustomer(@RequestBody RegisterRequest customer) {
        return ResponseEntity.ok(customerService.registerCustomer(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> loginCustomer(@RequestBody LoginRequest customer) {
        return ResponseEntity.ok(customerService.loginCustomer(customer));
    }

    @GetMapping("/get-customer/{customer_id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customer_id) {
        return ResponseEntity.ok(customerService.getCustomer(customer_id));
    }
}
