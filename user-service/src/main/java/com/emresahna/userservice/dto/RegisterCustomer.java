package com.emresahna.userservice.dto;

import lombok.Data;

@Data
public class RegisterCustomer {
    private String customer_name;
    private String password;
    private String phone;
    private String email;
    private String bank_details;
}