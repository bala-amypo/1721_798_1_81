package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")   // ✅ FIX: avoid H2 reserved keyword "user"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String department;
    private String role;

    public Long getId() { 
        return id; 
    }

    public String getEmail() { 
        return email; 
    }

    public String getPassword() { 
        return password; 
    }

    public String getDepartment() { 
        return department; 
    }

    public String getRole() { 
        return role; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }

    public void setDepartment(String department) { 
        this.department = department; 
    }

    public void setRole(String role) { 
        this.role = role; 
    }
}
