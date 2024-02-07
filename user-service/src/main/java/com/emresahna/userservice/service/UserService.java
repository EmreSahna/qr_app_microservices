package com.emresahna.userservice.service;

import com.emresahna.userservice.dto.LoginRequest;
import com.emresahna.userservice.dto.RegisterUserRequest;
import com.emresahna.userservice.dto.UserNotificationInfo;
import com.emresahna.userservice.entity.User;

public interface UserService {
    User registerUser(RegisterUserRequest registerUserRequest);
    User loginUser(LoginRequest loginRequest);
    User getUser(String userId);
    String getUserEmail(String userId);
    UserNotificationInfo getUserNotificationInfoById(String id);
}
