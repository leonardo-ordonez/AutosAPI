package com.programacionmovil.AutosAPI.Controlador;

import com.programacionmovil.AutosAPI.Modelo.User;
import com.programacionmovil.AutosAPI.Modelo.Vehicle;
import com.programacionmovil.AutosAPI.Servicio.UserService;
import com.programacionmovil.AutosAPI.Servicio.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private static final Logger logger = Logger.getLogger(VehicleController.class.getName());

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserService userService;

    @GetMapping("/listar")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        logger.info("GET request to fetch all vehicles");
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        logger.info("Fetched " + vehicles.size() + " vehicles");
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping("/agregar/{userId}")
    public ResponseEntity<Vehicle> addVehicle(@PathVariable Integer userId, @RequestBody Vehicle vehicle) {
        logger.info("POST request to add vehicle for user ID: " + userId);
        User user = userService.findUserById(userId);
        if (user == null) {
            logger.warning("User not found: " + userId);
            return ResponseEntity.badRequest().build();
        }
        logger.info("User found: " + user);
        vehicle.setUser(user);
        Vehicle newVehicle = vehicleService.addVehicle(vehicle);
        logger.info("Added vehicle: " + newVehicle);
        return ResponseEntity.ok(newVehicle);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        logger.info("DELETE request to delete vehicle with ID: " + id);
        vehicleService.deleteVehicle(id);
        logger.info("Deleted vehicle with ID: " + id);
        return ResponseEntity.noContent().build();
    }
}