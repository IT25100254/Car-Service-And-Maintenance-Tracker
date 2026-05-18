package com.example.car_service.model;

public class DashboardData {
    private int totalVehicles;
    private int activeServices;
    private double totalRevenue;

    public DashboardData(int totalVehicles, int activeServices, double totalRevenue) {
        this.totalVehicles = totalVehicles;
        this.activeServices = activeServices;
        this.totalRevenue = totalRevenue;
    }

    public int getTotalVehicles() { return totalVehicles; }
    public int getActiveServices() { return activeServices; }
    public double getTotalRevenue() { return totalRevenue; }
}