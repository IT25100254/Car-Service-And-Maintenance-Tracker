package com.autocare.services;

import com.autocare.data.TextDBHandler;
import com.autocare.models.User;

import java.util.List;
import java.util.UUID;

public class UserManager {

    public boolean registerUser(User user) {
        // Simple validation: check if username already exists
        List<User> existingUsers = TextDBHandler.getAllUsers();
        for (User u : existingUsers) {
            if (u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail())) {
                return false; // Username or email taken
            }
        }
        
        // Generate a unique ID if not present
        if(user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        
        return TextDBHandler.saveUser(user);
    }

    public User loginUser(String identifier, String password) {
        List<User> users = TextDBHandler.getAllUsers();
        for (User u : users) {
            if ((u.getUsername().equals(identifier) || u.getEmail().equals(identifier)) 
                && u.getPassword().equals(password)) {
                return u; // Login successful
            }
        }
        return null; // Login failed
    }

    public boolean updateUser(User updatedUser) {
        List<User> users = TextDBHandler.getAllUsers();
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                found = true;
                break;
            }
        }
        if (found) {
            return TextDBHandler.saveAllUsers(users);
        }
        return false;
    }

    public boolean deleteUser(String userId) {
        List<User> users = TextDBHandler.getAllUsers();
        boolean removed = users.removeIf(u -> u.getId().equals(userId));
        if (removed) {
            return TextDBHandler.saveAllUsers(users);
        }
        return false;
    }

    public boolean resetPassword(String identifier, String newPassword) {
        List<User> users = TextDBHandler.getAllUsers();
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getUsername().equals(identifier) || u.getEmail().equals(identifier)) {
                u.setPassword(newPassword);
                users.set(i, u);
                found = true;
                break;
            }
        }
        if (found) {
            return TextDBHandler.saveAllUsers(users);
        }
        return false;
    }

    public User searchUser(String query) {
        if (query == null || query.trim().isEmpty()) return null;
        List<User> users = TextDBHandler.getAllUsers();
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(query) || u.getEmail().equalsIgnoreCase(query)) {
                return u;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return TextDBHandler.getAllUsers();
    }
}
