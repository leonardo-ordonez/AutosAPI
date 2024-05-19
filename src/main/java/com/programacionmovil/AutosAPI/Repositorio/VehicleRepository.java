package com.programacionmovil.AutosAPI.Repositorio;

import com.programacionmovil.AutosAPI.Modelo.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
