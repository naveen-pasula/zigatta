package com.zigatta.siriusXM.zigattaVehicleApp.controller;

import com.zigatta.siriusXM.zigattaVehicleApp.ZigattaVehicleAppApplicationTests;
import com.zigatta.siriusXM.zigattaVehicleApp.models.Vehicle;
import com.zigatta.siriusXM.zigattaVehicleApp.services.VehicleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class VehicleControllerTest extends ZigattaVehicleAppApplicationTests {

    @Autowired
    WebApplicationContext applicationContext;

    MockMvc mockMvc;

    @MockBean
    VehicleService vehicleService;


    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void vehicleIdTest() throws Exception {
        Vehicle vehicle = new Vehicle("1A4AABBC5KD501999",2019,"FCA","RAM","AUTO");
        String mockData = "{\"vin\":\"1A4AABBC5KD501999\",\"year\": 2019,\"make\": \"FCA\",\"model\": \"RAM\", \"transmissionType\": \"MANUAL\"}";


        when(vehicleService.createVehicle(Mockito.any(Vehicle.class))).thenReturn(CompletableFuture.completedFuture(vehicle.getVin()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vehicle-api/v1/vehicles/vehicle")
                                        .contentType("application/json")
                                        .content(mockData);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());


    }

    @Test
    public void vehicleTransmissionTypeTest() throws Exception {
        String mockData = "{\"vin\":\"1A4AABBC5KD501999\",\"year\": 2019,\"make\": \"FCA\",\"model\": \"RAM\", \"transmissionType\": \"man\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vehicle-api/v1/vehicles/vehicle")
                .contentType("application/json")
                .content(mockData);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
        Assert.assertEquals("Transmission type should be MANUAL or AUTO",result.getResolvedException().getMessage());


    }

    @Test
    public void invalidVehicleObjectTest() throws Exception {
        String mockData = "{\"vin\":null,\"year\": 2019,\"make\": \"FCA\",\"model\": \"RAM\", \"transmissionType\": \"man\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vehicle-api/v1/vehicles/vehicle")
                .contentType("application/json")
                .content(mockData);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(),response.getStatus());
        Assert.assertEquals("Payload attributes cannot be null",result.getResolvedException().getMessage());


    }
}
