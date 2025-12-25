package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    // Registration
    User registerUser(User user);

    // Fetch operations (USED BY CONTROLLERS)
    User getByEmail(String email);

    User getUser(Long id);

    List<User> getAllUsers();
}
