package com.example.demo.dto;

public class RegisterRequest {
    private String email;
    private String password;
    private String department;
    private String role;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setDepartment(String department) { this.department = department; }
    public void setRole(String role) { this.role = role; }
}
