package com.programacionmovil.AutosAPI.Servicio;

import com.programacionmovil.AutosAPI.Modelo.Vehicle;
import com.programacionmovil.AutosAPI.Repositorio.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class VehicleService {

    private static final Logger logger = Logger.getLogger(VehicleService.class.getName());

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        logger.info("Fetching all vehicles");
        List<Vehicle> vehicles = vehicleRepository.findAll();
        logger.info("Fetched " + vehicles.size() + " vehicles");
        return vehicles;
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        logger.info("Adding vehicle: " + vehicle);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        logger.info("Added vehicle: " + savedVehicle);
        return savedVehicle;
    }

    public void deleteVehicle(Integer id) {
        logger.info("Deleting vehicle with ID: " + id);
        vehicleRepository.deleteById(id);
        logger.info("Deleted vehicle with ID: " + id);
    }

    public List<Vehicle> getVehiclesByUserId(Integer userId) {
        logger.info("Fetching vehicles for user ID: " + userId);
        List<Vehicle> vehicles = vehicleRepository.findByUserId(userId);
        logger.info("Fetched " + vehicles.size() + " vehicles for user ID: " + userId);
        return vehicles;
    }

    public Vehicle updateVehicle(Integer id, Vehicle vehicleDetails) {
        logger.info("Updating vehicle with ID: " + id);
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setType(vehicleDetails.getType());
            vehicle.setBrand(vehicleDetails.getBrand());
            vehicle.setMileage(vehicleDetails.getMileage());
            vehicle.setColor(vehicleDetails.getColor());
            vehicle.setPlate(vehicleDetails.getPlate());
            vehicle.setDescription(vehicleDetails.getDescription());
            vehicle.setImagen(vehicleDetails.getImagen());
            Vehicle updatedVehicle = vehicleRepository.save(vehicle);
            logger.info("Updated vehicle: " + updatedVehicle);
            return updatedVehicle;
        }).orElse(null);
    }
}