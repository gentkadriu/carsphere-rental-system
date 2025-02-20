package com.example.carsphere.controllers;

import com.example.carsphere.models.Vehicle;
import com.example.carsphere.services.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CartController {

    private final VehicleService vehicleService;

    public CartController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
       
        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "redirect:/cart"; 
    }
}
