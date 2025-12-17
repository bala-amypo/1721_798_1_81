package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User registerUser(User user) {
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
