package com.example.carsphere.controllers;

import com.example.carsphere.models.Vehicle;
import com.example.carsphere.repositories.VehicleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final VehicleRepository vehicleRepository;

    public AdminController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "admin/dashboard";
    }

    @PostMapping("/add")
    public String addVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editVehicle(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehicleRepository.findById(id).orElseThrow());
        return "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateVehicle(@PathVariable Long id, @ModelAttribute Vehicle updatedVehicle) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow();
        vehicle.setName(updatedVehicle.getName());
        vehicle.setMake(updatedVehicle.getMake());
        vehicle.setColor(updatedVehicle.getColor());
        vehicle.setYear(updatedVehicle.getYear());
        vehicle.setPricePerDay(updatedVehicle.getPricePerDay());
        vehicle.setImageUrl(updatedVehicle.getImageUrl());
        vehicleRepository.save(vehicle);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}
