package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import java.util.Map;

public interface UserService {
    Map<String, String> login(LoginRequest request);
}
