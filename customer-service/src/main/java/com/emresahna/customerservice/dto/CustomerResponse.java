package com.emresahna.customerservice.dto;

import lombok.Data;

@Data
public class CustomerResponse {
    private String customer_name;
    private String phone;
    private String email;
    private String bank_details;
}