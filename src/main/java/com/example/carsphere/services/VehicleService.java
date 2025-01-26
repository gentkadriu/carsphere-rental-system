package com.example.carsphere.services;

import com.example.carsphere.models.Vehicle;
import com.example.carsphere.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    // Constructor for dependency injection
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Fetch all vehicles from the repository
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Fetch a vehicle by its ID
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with ID " + id + " does not exist"));
    }

    // Save a new vehicle or update an existing one
    public Vehicle saveVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        return vehicleRepository.save(vehicle);
    }

    // Delete a vehicle by its ID
    public void deleteVehicle(Long id) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            vehicleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Vehicle with ID " + id + " does not exist");
        }
    }
}
