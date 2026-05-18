package com.example.car_service.controller;

import com.example.car_service.model.DashboardData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @GetMapping("/summary")
    public DashboardData getSummary() {
        int totalVehicles = 42;
        int activeServices = 8;
        double totalRevenue = 185000.00;

        // 💡 HIGHLIGHT: මෙතන තිබ්බ සෙමිකෝලන් (;) වෙනුවට දැන් කොමා (,) දාලා සම්පූර්ණයෙන්ම හදලා තියෙන්නේ.
        return new DashboardData(totalVehicles, activeServices, totalRevenue);
    }
}