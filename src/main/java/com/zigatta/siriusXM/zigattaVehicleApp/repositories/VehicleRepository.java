package com.zigatta.siriusXM.zigattaVehicleApp.repositories;


import com.zigatta.siriusXM.zigattaVehicleApp.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
