package com.example.carsphere.config;

import com.example.carsphere.models.User;
import com.example.carsphere.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner createDefaultUsers(UserRepository userRepository) {
        return args -> {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Create admin user if not already present
            if (userRepository.findByUsername("admin") == null) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("adminpassword")); // Hash the password
                userRepository.save(adminUser);
                System.out.println("Admin user created: admin/adminpassword");
            } else {
                System.out.println("Admin user already exists.");
            }

            // Create customer user if not already present
            if (userRepository.findByUsername("customer") == null) {
                User customerUser = new User();
                customerUser.setUsername("customer");
                customerUser.setPassword(passwordEncoder.encode("customerpassword")); // Hash the password
                userRepository.save(customerUser);
                System.out.println("Customer user created: customer/customerpassword");
            } else {
                System.out.println("Customer user already exists.");
            }
        };
    }
}
