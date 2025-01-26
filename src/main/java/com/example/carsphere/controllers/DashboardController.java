//package com.example.carsphere.controllers;
//
//import com.example.carsphere.models.Vehicle;
//import com.example.carsphere.repositories.VehicleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/admin")
//public class DashboardController {
//
//    @Autowired
//    private VehicleRepository vehicleRepository;
//
//    // Show the dashboard
//    @GetMapping("/dashboard/delete/{id}")
//    public String deleteVehicleFromDashboard(@PathVariable Long id) {
//        vehicleRepository.deleteById(id);
//        return "redirect:/admin/dashboard";
//    }
//
//
//    // Add a new vehicle
//    @PostMapping("/add")
//    public String addVehicle(@RequestParam String name, @RequestParam double pricePerDay) {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setName(name);
//        vehicle.setPricePerDay(pricePerDay);
//        vehicleRepository.save(vehicle);
//        return "redirect:/admin/dashboard"; // Redirect to refresh the dashboard
//    }
//
//    // Delete a vehicle
//    @GetMapping("/delete/{id}")
//    public String deleteVehicle(@PathVariable Long id) {
//        vehicleRepository.deleteById(id);
//        return "redirect:/admin/dashboard"; // Redirect to refresh the dashboard
//    }
//}
