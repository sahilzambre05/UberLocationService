package com.example.UberLocationService.controller;

import com.example.UberLocationService.dto.DriverLocationDto;
import com.example.UberLocationService.dto.NearbyDriversRequestDto;
import com.example.UberLocationService.dto.SaveDriverLocationRequestDto;
import com.example.UberLocationService.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/drivers")
    public ResponseEntity<Boolean> saveDriverLocation(@RequestBody SaveDriverLocationRequestDto saveDriverLocationRequestDto) {
        Boolean response = locationService.saveDriverLocation(saveDriverLocationRequestDto.getDriverId(), saveDriverLocationRequestDto.getLatitude(), saveDriverLocationRequestDto.getLongitude());
        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }


    @GetMapping ("/nearby/drivers")
    public ResponseEntity<List<DriverLocationDto>> getNearbyDrivers(@RequestBody NearbyDriversRequestDto nearbyDriversRequestDto) {
        try {
            List<DriverLocationDto> drivers = locationService.getNearByDrivers(nearbyDriversRequestDto.getLatitude(), nearbyDriversRequestDto.getLongitude());
            return new ResponseEntity<>(drivers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}