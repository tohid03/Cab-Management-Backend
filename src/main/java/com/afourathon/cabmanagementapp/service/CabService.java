package com.afourathon.cabmanagementapp.service;

import com.afourathon.cabmanagementapp.model.Cab;
import com.afourathon.cabmanagementapp.model.Driver;

import java.util.List;

public interface CabService {
    Cab addCab(Cab cab);
    List<Cab> getCabs();
    Cab getCabById(Long cabId);
    Cab ubdateCab(Long cabId,Cab cab);
    boolean deleteCab(Long cabId);
    Cab updateDriverCab(Long cabId, Long driverId);
    void removeCabDriver(Long cabId);
}
