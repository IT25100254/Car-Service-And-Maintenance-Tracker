package com.autocare.models;

public class RegularUser extends User {

    public RegularUser(String id, String fullName, String username, String email, String password) {
        super(id, fullName, username, email, password, "REGULAR");
    }

    @Override
    public String getDashboardAccessLevel() {
        return "Customer Dashboard: View own vehicles, schedule services, view history.";
    }
}
