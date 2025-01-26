package com.example.carsphere.controllers;

import com.example.carsphere.models.User;
import com.example.carsphere.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.registerUser(user)) {
            model.addAttribute("successMessage", "Registration successful! Please login.");
            return "login";
        } else {
            model.addAttribute("errorMessage", "Username already exists. Try another.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        if (userService.validateUser(user)) {
            return "redirect:/admin/dashboard"; // Redirect to admin dashboard after login
        } else {
            model.addAttribute("errorMessage", "Invalid username or password.");
            return "login";
        }
    }
}
