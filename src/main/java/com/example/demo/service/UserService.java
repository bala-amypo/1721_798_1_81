package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;

import java.util.Map;

public interface UserService {
    User registerUser(RegisterRequest request);
    Map<String, String> login(LoginRequest request);
}
