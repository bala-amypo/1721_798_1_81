package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User registerUser(User user);
    User login(String email, String password);
}
