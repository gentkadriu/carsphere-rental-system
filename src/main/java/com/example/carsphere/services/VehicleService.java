package com.example.carsphere.services;

import com.example.carsphere.models.Vehicle;
import com.example.carsphere.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with ID " + id + " does not exist"));
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Vehicle with ID " + id + " does not exist");
        }
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findAll();
    }
}
