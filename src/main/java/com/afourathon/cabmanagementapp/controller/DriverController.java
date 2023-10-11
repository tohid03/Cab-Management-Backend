package com.afourathon.cabmanagementapp.controller;

import com.afourathon.cabmanagementapp.model.Cab;
import com.afourathon.cabmanagementapp.model.Driver;
import com.afourathon.cabmanagementapp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cab-management")
@CrossOrigin("*")
public class DriverController {


    private final DriverService driverService;
    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) Integer pageNo,
            @RequestParam(value="pageSize",defaultValue = "5",required = false) Integer pageSize) {
        List<Driver> drivers = driverService.getDrivers(pageNo,pageSize);
        if(drivers.isEmpty())
            return new ResponseEntity<>(drivers, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping(path = "/drivers/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        Driver driver = driverService.getDriverById(id);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @PostMapping("/drivers")
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        Driver driver1 = driverService.addDriver(driver);
        return new ResponseEntity<>(driver1, HttpStatus.CREATED);
    }

    @PutMapping("/drivers/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable long id, @RequestBody Driver driver) {
        Driver driver1 = driverService.updateDriver(id, driver);
        return new ResponseEntity<>(driver1, HttpStatus.OK);
    }

    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<Boolean> deleteDriver(@PathVariable long id) {
        boolean flag = driverService.deleteDriver(id);
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    @PutMapping("/drivers/{driverId}/cab/{cabId}")
    public ResponseEntity<Driver> addDriverToCab(@PathVariable Long driverId, @PathVariable Long cabId){
        Driver driver = driverService.assignedCab(cabId,driverId);
        return new ResponseEntity<>(driver,HttpStatus.CREATED);
    }
    @DeleteMapping("/drivers/unassignedCab/{driverId}")
    public ResponseEntity<Driver> removeCabDriver(@PathVariable Long driverId){
        driverService.unassignedCab(driverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
