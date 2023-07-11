package com.emresahna.userservice.controller;

import com.emresahna.userservice.dto.LoginRequest;
import com.emresahna.userservice.dto.RegisterUserRequest;
import com.emresahna.userservice.entity.User;
import com.emresahna.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserRequest user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest user){
        return ResponseEntity.ok(userService.loginUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping("/get-user-email/{userId}")
    public String getUserEmail(@PathVariable String userId){
        return userService.getUserEmail(userId);
    }
}
