package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;

public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegisterRequest request) {
        User user = new User(
                null,
                request.getName(),
                request.getEmail(),
                request.getDepartment(),
                "USER",
                request.getPassword(),
                null
        );
        userService.registerUser(user);
        return "REGISTERED";
    }

    public String login(LoginRequest request) {
        return jwtUtil.generateToken(
                java.util.Map.of(),
                request.getEmail()
        );
    }
}
