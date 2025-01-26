package com.example.carsphere.controllers;

import com.example.carsphere.models.Vehicle;
import com.example.carsphere.services.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final VehicleService vehicleService;

    public AdminController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "admin/dashboard";
    }

    @PostMapping("/add")
    public String addVehicle(
            @RequestParam String name,
            @RequestParam String make,
            @RequestParam String color,
            @RequestParam int year,
            @RequestParam double pricePerDay) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(name);
        vehicle.setMake(make);
        vehicle.setColor(color);
        vehicle.setYear(year);
        vehicle.setPricePerDay(pricePerDay);
        vehicleService.saveVehicle(vehicle);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "admin/edit"; // Navigate to the edit form
    }

    @PostMapping("/edit/{id}")
    public String updateVehicle(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String make,
            @RequestParam String color,
            @RequestParam int year,
            @RequestParam double pricePerDay) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        vehicle.setName(name);
        vehicle.setMake(make);
        vehicle.setColor(color);
        vehicle.setYear(year);
        vehicle.setPricePerDay(pricePerDay);
        vehicleService.saveVehicle(vehicle);
        return "redirect:/admin/dashboard";
    }







}
