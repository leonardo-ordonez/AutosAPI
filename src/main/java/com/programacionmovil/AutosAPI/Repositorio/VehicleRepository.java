package com.programacionmovil.AutosAPI.Repositorio;

import com.programacionmovil.AutosAPI.Modelo.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByUserId(Integer userId);
}
