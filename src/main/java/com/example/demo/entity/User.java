package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String department;
    private String role;
    private String password;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "currentHolder")
    private List<Asset> assets;

    public User() {}

    public User(Long id, String fullName, String email, String department,
                String role, String password, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.role = role;
        this.password = password;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = "USER";
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }

public String getFullName() { return fullName; }

public String getEmail() { return email; }

public String getDepartment() { return department; }

public String getRole() { return role; }

public String getPassword() { return password; }

public void setPassword(String password) { this.password = password; }

public void setRole(String role) { this.role = role; }

}
