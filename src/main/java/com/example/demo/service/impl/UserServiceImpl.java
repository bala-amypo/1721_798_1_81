package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User registerUser(User user) {
        if (repo.existsByEmail(user.getEmail()))
            throw new ValidationException("Email already in use");

        if (user.getPassword().length() < 8)
            throw new ValidationException("Password must be at least 8 characters");

        if (user.getDepartment() == null)
            throw new ValidationException("Department is required");

        user.prePersist();
        user.setId(null);
        return repo.save(user);
    }
}
