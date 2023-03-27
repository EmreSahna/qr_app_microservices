package com.emresahna.userservice.controller;

import com.emresahna.userservice.dto.LoginRequest;
import com.emresahna.userservice.dto.RegisterCustomer;
import com.emresahna.userservice.entity.Customer;
import com.emresahna.userservice.service.CustomerService;
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
    public ResponseEntity<Customer> createCustomer(@RequestBody RegisterCustomer customer) {
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
