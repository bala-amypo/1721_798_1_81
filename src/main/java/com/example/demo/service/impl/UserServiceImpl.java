package com.example.demo.service.impl;

import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new ValidationException("Email already in use");

        if (user.getPassword().length() < 8)
            throw new ValidationException("Password must be at least 8 characters");

        if (user.getDepartment() == null)
            throw new ValidationException("Department is required");

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
