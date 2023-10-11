package com.afourathon.cabmanagementapp.service;

import com.afourathon.cabmanagementapp.model.Driver;

import java.util.List;

public interface DriverService{
    Driver addDriver(Driver driver);

    List<Driver> getDrivers(int pageSize,int pageNo);

    Driver getDriverById(Long driverId);

    Driver updateDriver(Long driverId, Driver driver);

    boolean deleteDriver(Long driverId);

    Driver assignedCab(Long driverId,Long cabId);
    Driver unassignedCab(Long driverId);

}
