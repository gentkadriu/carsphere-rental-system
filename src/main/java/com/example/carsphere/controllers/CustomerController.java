package com.example.carsphere.controllers;

import com.example.carsphere.models.Vehicle;
import com.example.carsphere.services.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {

    private final VehicleService vehicleService;

    public CustomerController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public String customerHome(Model model) {
        // Fetch all vehicles
        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();

        // Pass the data to the view
        model.addAttribute("vehicles", availableVehicles);

        return "index";
    }
}
