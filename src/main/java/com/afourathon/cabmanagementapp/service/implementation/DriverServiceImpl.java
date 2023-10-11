package com.afourathon.cabmanagementapp.service.implementation;

import com.afourathon.cabmanagementapp.exception.ResourceNotFoundException;
import com.afourathon.cabmanagementapp.model.Cab;
import com.afourathon.cabmanagementapp.model.Driver;
import com.afourathon.cabmanagementapp.repository.CabRepository;
import com.afourathon.cabmanagementapp.repository.DriverRepository;
import com.afourathon.cabmanagementapp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final CabRepository cabRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, CabRepository cabRepository) {
        this.driverRepository = driverRepository;
        this.cabRepository = cabRepository;
    }

    @Override
    public Driver addDriver(Driver driver) {
        final String driverId = "DRIV000";
        int count = (int) driverRepository.count()+1;
        driver.setIdNumber(driverId+count);
        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> getDrivers(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Driver> drivers = driverRepository.findAll(pageable);
        return drivers.getContent();
    }

    @Override
    public Driver getDriverById(Long driverId) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        return driver.orElseThrow(
                () -> new ResourceNotFoundException("Driver not exist with id:" + driverId)
        );
    }

    @Override
    public Driver updateDriver(Long driverId, Driver driver) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isPresent()) {
            Driver driver1 = optionalDriver.get();
            driver1.setName(driver.getName());
            driver1.setEmail(driver.getEmail());
            driver1.setPhoneNumber(driver.getPhoneNumber());
            return driverRepository.save(driver1);
        } else {
            throw new ResourceNotFoundException("Driver not exist with id:" + driverId);
        }
    }

    @Override
    public boolean deleteDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver not exist with id:" + driverId)
        );
        driverRepository.deleteById(driverId);
        return true;
    }

    @Override
    public Driver assignedCab(Long driverId, Long cabId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver not exist with id:" + driverId));
        Cab cab = cabRepository.findById(cabId).orElseThrow(()->
                new ResourceNotFoundException("Driver not exist with id:" + cabId));
        driver.setCab(cab);
        driverRepository.save(driver);
        return driver;
    }

    @Override
    public Driver unassignedCab(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver not exist with id:" + driverId));
        driver.setCab(null);
        driverRepository.save(driver);
        return driver;
    }
}
