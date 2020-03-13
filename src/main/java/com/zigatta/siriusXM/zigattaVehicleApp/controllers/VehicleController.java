package com.zigatta.siriusXM.zigattaVehicleApp.controllers;

import com.zigatta.siriusXM.zigattaVehicleApp.exception.InvalidPayloadException;
import com.zigatta.siriusXM.zigattaVehicleApp.models.Vehicle;
import com.zigatta.siriusXM.zigattaVehicleApp.exception.ResourceException;
import com.zigatta.siriusXM.zigattaVehicleApp.services.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class VehicleController {

    Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    VehicleService vehicleService;

    @PostMapping(value = "/vehicle-api/v1/vehicles/vehicle", consumes = "application/json")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) throws InterruptedException, ExecutionException {
        System.out.println("Data" + vehicle.toString());
        logger.info("Request Payload {}", vehicle.toString());

        if (vehicle.getVin() == null || vehicle.getMake() == null || vehicle.getModel() == null || vehicle.getYear() == 0 || vehicle.getTransmissionType() == null) {
            throw new InvalidPayloadException(HttpStatus.INTERNAL_SERVER_ERROR, "Payload attributes cannot be null");
        } else if (!(vehicle.getTransmissionType().equals("MANUAL")) && !(vehicle.getTransmissionType().equals("AUTO"))) {
            throw new ResourceException(HttpStatus.BAD_REQUEST, "Transmission type should be MANUAL or AUTO");
        } else {
            return new ResponseEntity<>(vehicleService.createVehicle(vehicle).get(), HttpStatus.OK);
        }
    }
}
