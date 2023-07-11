package com.emresahna.userservice.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String fullName;
    private String password;
    private String phone;
    private String email;
    private Integer userType;
}
