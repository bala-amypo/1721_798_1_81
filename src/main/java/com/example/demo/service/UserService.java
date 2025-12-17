package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.User;

public interface UserService {

    User register(String email, String password, String department, String role);

    String login(LoginRequest request);
}
