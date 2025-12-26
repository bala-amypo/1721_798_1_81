package com.example.demo.repository;

import com.example.demo.entity.User;
import java.util.Optional;
import java.util.List;

public interface UserRepository {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    User save(User user);
    List<User> findAll();
}
