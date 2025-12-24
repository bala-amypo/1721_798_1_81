package com.example.demo.dto;

public class RegisterRequest {

    private String fullName;
    private String email;
    private String department;
    private String password;

    // No-arg constructor (REQUIRED)
    public RegisterRequest() {
    }

    // All-args constructor (used in tests)
    public RegisterRequest(String fullName, String email, String department, String password) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.password = password;
    }

    // ===== GETTERS =====
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getPassword() {
        return password;
    }

    // ===== SETTERS =====
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
