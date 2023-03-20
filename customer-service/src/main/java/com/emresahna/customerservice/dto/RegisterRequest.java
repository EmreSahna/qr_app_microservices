package com.emresahna.customerservice.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String customer_name;
    private String password;
    private String phone;
    private String email;
    private String bank_details;
}