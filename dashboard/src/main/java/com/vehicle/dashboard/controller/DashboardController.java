package com.vehicle.dashboard.controller;
import com.vehicle.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute(
                "vehicles",
                dashboardService.getAllVehicles()
        );

        return "dashboard";
    }
}


