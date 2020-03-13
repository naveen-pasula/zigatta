# Zigatta Vehicle Project

## Prerequisites
```bash
Java 8 or higher
Maven
```

## To Run the test case
```bash
mvn clean
mvn test
```

## To Run the Application
```bash
mvn clean 
mvn spring-boot:run
```

## Description
```bash
@PostMapping("/vehicle-api/v1/vehicles/vehicle")
```
When above mentioned URL pattern with request body is called,Method with Signature
 ```addVehicle(Vehicle vehicle)``` in ```VehicleController``` is executed.
 This method checks for null and specfic value constraints and if found valid, 
 it passes the RequestBody to ```createService``` method of  ```VehicleService``` class as  an argument.
  ```createService``` method is an async function whose configuration is specified in ```AsyncConfiguration``` 
  class and saves the data in the table. 
  
## TestCases
 TestCase 1:
 Return Success(OK 200) Response for valid Payload.
 
 TestCase 2:
 Return Error(BadRequest 400) Response if Transmission Type is neither "AUTO" nor "MANUAL".
 
 TestCase 3:
 Return Error(InternalServerError 500) Response if any of the value in payload is null. 