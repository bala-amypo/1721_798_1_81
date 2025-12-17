package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    public Long getId() { return id; }
public String getEmail() { return email; }
public String getPassword() { return password; }
public String getDepartment() { return department; }
public String getRole() { return role; }

}
