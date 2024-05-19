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
        return vehicleRepository.findAll();
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        logger.info("Adding vehicle: " + vehicle);
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Integer id) {
        logger.info("Deleting vehicle with ID: " + id);
        vehicleRepository.deleteById(id);
    }
}