package com.example.carsphere.controllers;

import com.example.carsphere.models.Vehicle;
import com.example.carsphere.services.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {

    private final VehicleService vehicleService;

    public CustomerController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/customerview")
    public String customerView(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "customerview";
    }

    @GetMapping("/cart/{id}")
    public String cart(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle); // Ensure the vehicle object contains pricePerDay
        return "cart";
    }
}
