package com.autocare.models;

public class AdminUser extends User {

    public AdminUser(String id, String fullName, String username, String email, String password) {
        super(id, fullName, username, email, password, "ADMIN");
    }

    @Override
    public String getDashboardAccessLevel() {
        return "Admin Dashboard: Manage all users, all vehicles, global service reports.";
    }
}
