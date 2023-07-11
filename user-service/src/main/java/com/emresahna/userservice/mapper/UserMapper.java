package com.emresahna.userservice.mapper;

import com.emresahna.userservice.dto.RegisterUserRequest;
import com.emresahna.userservice.entity.User;
import com.emresahna.userservice.entity.UserType;

public class UserMapper {
    public static User mapRegisterUserRequestToUser(RegisterUserRequest registerUserRequest){
        return User.builder()
                .fullName(registerUserRequest.getFullName())
                .password(registerUserRequest.getPassword())
                .phone(registerUserRequest.getPhone())
                .email(registerUserRequest.getEmail())
                .userType(getUserTypeByValue(registerUserRequest.getUserType()))
                .build();
    }

    private static UserType getUserTypeByValue(int value){
        for (UserType userType : UserType.values()){
            if (userType.getValue() == value){
                return userType;
            }
        }
        throw new IllegalArgumentException("No UserType with value " + value + " exists");
    }
}
