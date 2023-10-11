package com.afourathon.cabmanagementapp.service;

import com.afourathon.cabmanagementapp.model.Cab;

import java.util.List;

public interface CabService {
    Cab addCab(Cab cab);
    List<Cab> getCabs();
    Cab getCabById(Long cabId);
    Cab ubdateCab(Long cabId,Cab cab);
    boolean deleteCab(Long cabId);
    Cab assignedDriver(Long cabId, Long driverId);
    void unassignedDriver(Long cabId);
}
