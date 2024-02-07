package com.emresahna.userservice.service.impl;

import com.emresahna.userservice.dto.LoginRequest;
import com.emresahna.userservice.dto.RegisterUserRequest;
import com.emresahna.userservice.dto.UserNotificationInfo;
import com.emresahna.userservice.entity.User;
import com.emresahna.userservice.mapper.UserMapper;
import com.emresahna.userservice.repository.UserRepository;
import com.emresahna.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User registerUser(RegisterUserRequest registerUserRequest){
        return userRepository.save(UserMapper.mapRegisterUserRequestToUser(registerUserRequest));
    }

    @Override
    public User loginUser(LoginRequest loginRequest){
        return userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @Override
    public User getUser(String userId){
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public String getUserEmail(String userId){
        return getUser(userId).getEmail();
    }

    @Override
    public UserNotificationInfo getUserNotificationInfoById(String id) {
        User user = getUser(id);
        UserNotificationInfo userNotificationInfo = new UserNotificationInfo();
        userNotificationInfo.setEmail(user.getEmail());
        userNotificationInfo.setFullName(user.getFullName());
        return userNotificationInfo;
    }
}
