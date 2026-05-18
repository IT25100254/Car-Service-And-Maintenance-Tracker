package com.autocare.models;

public abstract class User {
    private String id;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String role; // "REGULAR" or "ADMIN"

    public User(String id, String fullName, String username, String email, String password, String role) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Encapsulation: Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Polymorphic method
    public abstract String getDashboardAccessLevel();

    @Override
    public String toString() {
        return id + "," + fullName + "," + username + "," + email + "," + password + "," + role;
    }
}
