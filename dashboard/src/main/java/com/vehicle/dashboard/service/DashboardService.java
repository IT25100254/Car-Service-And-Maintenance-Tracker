package com.vehicle.dashboard.service;

import com.vehicle.dashboard.model.vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private List<vehicle> vehicles = new ArrayList<>();

    public DashboardService() {

        vehicles.add(
                new vehicle("CAB-1234", "Toyota Prius", 18.5)
        );

        vehicles.add(
                new vehicle("CAR-4567", "Honda Vezel", 15.2)
        );
    }

    public List<vehicle> getAllVehicles() {
        return vehicles;
    }
}