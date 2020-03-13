package com.zigatta.siriusXM.zigattaVehicleApp.services;

import com.zigatta.siriusXM.zigattaVehicleApp.models.Vehicle;
import com.zigatta.siriusXM.zigattaVehicleApp.repositories.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    Logger logger = LoggerFactory.getLogger(VehicleService.class);

    @Async("asyncExecutor")
    public CompletableFuture<String> createVehicle(Vehicle vehicle) throws InterruptedException {
        logger.info("Vehicle ID: {}", vehicle.getVin());
        vehicleRepository.save(vehicle);
        Thread.sleep(1000L);
        logger.info("Vehicle Post Data: {}", vehicle.toString());
        return CompletableFuture.completedFuture(vehicle.getVin());

    }

}
