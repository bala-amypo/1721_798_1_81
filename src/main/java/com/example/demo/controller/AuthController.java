package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtUtil;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.LoginRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        User u = new User();
        u.setEmail(req.email);
        u.setDepartment(req.department);
        u.setPassword(req.password);
        return userService.registerUser(u);
    }

   @PostMapping("/login")
public Map<String, String> login(@RequestBody LoginRequest req) {
    String token = userService.login(req);
    return Map.of("token", token);
}

}
