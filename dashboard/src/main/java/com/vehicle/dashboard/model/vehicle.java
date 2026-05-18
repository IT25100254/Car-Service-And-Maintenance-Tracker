package com.vehicle.dashboard.model;

public class vehicle {
    private String vehicleNumber;
    private String model;
    private double fuelEconomy;

    public vehicle(String vehicleNumber, String model, double fuelEconomy) {
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.fuelEconomy = fuelEconomy;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getModel() {
        return model;
    }

    public double getFuelEconomy() {
        return fuelEconomy;
    }
}

